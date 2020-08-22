/*
    Stephen McMullan x19139497@student.ncirl.ie

    SSP Data Import Application

    Processes CSV dataset from AWS S3 Bucket to Kafka (and optionally Elasticsearch
 */

package ie.ncirl.sspproject.dataimport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.http.HttpHost;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class SSPDataImport {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSPDataImport.class);

    private static final Properties prop = new Properties();
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String AWS_BUCKET_NAME = "aws.bucketname";
    private static final String AWS_OBJECT_PREFIX = "aws.object.prefix";
    private static final String KAFKA_PERSIST = "kafka.persist";
    private static final String KAFKA_SERVER = "kafka.server";
    private static final String KAFKA_TOPIC = "kafka.topic";
    private static final String ES_PERSIST = "es.persist";
    private static final String ES_SERVER = "es.server";
    private static final String ES_PORT = "es.port";
    private static final String ES_SCHEME = "es.scheme";
    private static final String ES_INDEX = "es.index";
    private static final String ES_BULK_OFFSET = "es.bulk.offset";

    private static String bucketName = null;
    private static String objectPrefix = null;
    private static final Region region = Region.EU_WEST_1;

    private static Producer<String, JsonNode> producer = null;
    private static Boolean kafkaPersist = false;
    private static String kafkaServer = null;
    private static String kafkaTopic = null;

    private static RestHighLevelClient esClient = null;
    private static ActionListener<BulkResponse> esListener = null;
    private static BulkRequest esBulkRequest = null;
    private static Boolean esPersist = false;
    private static String esServer = null;
    private static String esPort = null;
    private static String esScheme = null;
    private static String esIndex = null;
    private static int esBulkOffset = 0;

    private static long startTime = 0;
    private static int processedFiles = 0;
    private static int processedRecords = 0;

    private static final int MILLIS_IN_SECOND = 1000;
    private static final int MILLIS_IN_MIN = MILLIS_IN_SECOND * 60;
    private static final int MILLIS_IN_HOUR = MILLIS_IN_MIN * 60;
    private static final int MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    private static final int MILLIS_IN_WEEK = MILLIS_IN_DAY * 7;
    private static final DateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();

        // Load the Kafka broker and Elasticsearch instance connection properties
        loadConfigProperties();

        // Initialise the connection to Kafka and Elasticsearch
        initSinkConnection();

        // Retrieve and process the CSV files from AWS S3 and send to Kafka and Elasticsearch
        processAWSObjects();

        // Close the connection to Kafka and Elasticsearch
        closeSinkConnection();

        // Print some stats regarding run time and processing rates
        printStats();
    }

    private static void loadConfigProperties() {
        try {
            prop.load(Objects.requireNonNull(SSPDataImport.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)));

            // AWS S3 Bucket location
            bucketName = prop.getProperty(AWS_BUCKET_NAME);
            objectPrefix = prop.getProperty(AWS_OBJECT_PREFIX);

            // Kafka properties
            kafkaPersist = Boolean.parseBoolean(prop.getProperty(KAFKA_PERSIST));
            kafkaServer = prop.getProperty(KAFKA_SERVER);
            kafkaTopic = prop.getProperty(KAFKA_TOPIC);

            // Elasticsearch properties
            esPersist = Boolean.parseBoolean(prop.getProperty(ES_PERSIST));
            esServer = prop.getProperty(ES_SERVER);
            esPort = prop.getProperty(ES_PORT);
            esScheme = prop.getProperty(ES_SCHEME);
            esIndex = prop.getProperty(ES_INDEX);
            esIndex = prop.getProperty(ES_INDEX);
            esBulkOffset = Integer.parseInt(prop.getProperty(ES_BULK_OFFSET));
        } catch (IOException e) {
            LOGGER.error("Unable to load config.properties from classpath");
        }
    }

    private static void processAWSObjects() {
        // Use AWS SDK to connect to S3 bucket and stream in the S3 objects
        S3Client s3Client = S3Client
                .builder()
                .region(region)
                .credentialsProvider(AnonymousCredentialsProvider.create())
                .build();

        ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .bucket(bucketName)
                .build();

        ListObjectsResponse res = s3Client.listObjects(listObjects);
        List<S3Object> s3Objects = res.contents();

        for(S3Object s3Object : s3Objects) {
            String s3ObjectKey = s3Object.key();

            if(s3ObjectKey.startsWith(objectPrefix)) {
                LOGGER.info("Processing {}", s3ObjectKey);

                long fileStartTime = System.currentTimeMillis();

                try {
                    ResponseInputStream<GetObjectResponse> result = s3Client.getObject(
                            GetObjectRequest.builder().bucket(bucketName).key(s3ObjectKey).build(),
                            ResponseTransformer.toInputStream()
                    );

                    // Set up the CSV parsing of the S3 Object using the TelecomRecord class as a schema
                    CsvMapper csvMapper = new CsvMapper();
                    csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

                    CsvSchema telecomSchema = csvMapper
                            .schemaFor(TelecomRecord.class)
                            .withoutHeader()
                            .withColumnSeparator('\t');

                    MappingIterator<TelecomRecord> csvLines = csvMapper.readerFor(TelecomRecord.class)
                            .with(telecomSchema)
                            .readValues(result);

                    // Convert each TelecomRecord to a JSON object using Jackson library
                    ObjectMapper objectMapper = new ObjectMapper();
                    int recordNum = 0;

                    while (csvLines.hasNext()) {
                        TelecomRecord telecomRecord = csvLines.next();
                        JsonNode jsonNode = objectMapper.valueToTree(telecomRecord);

                        // Add the normalised timestamps to the record
                        // These consist of timestamps adjusted to the start of the previous hour, day and week
                        // to be used as aggregation keys
                        addTimestamps(jsonNode, telecomRecord.timestamp);

                        // Publish the record to Kafka (and optionally Elasticsearch)
                        publishRecordToSink(jsonNode);
                        recordNum++;

                        // Flush records to Elastisearch in a bulk loading manner esBulkOffset records at a time
                        if (esPersist) {
                            if (recordNum % esBulkOffset == 0) {
                                flushRecordstoEs();
                            }
                        }
                    }

                    // Flush the remainining records to Elastisearch
                    if (esPersist) {
                        flushRecordstoEs();
                    }

                    long fileProcessingTime = (System.currentTimeMillis() - fileStartTime)/1000;

                    LOGGER.info("Processed {} of size {} lines {} in {} secs at a rate of {} records/sec",
                            s3ObjectKey, s3Object.size(), recordNum, fileProcessingTime, recordNum/fileProcessingTime);

                    processedRecords += recordNum;
                } catch (Exception ex) {
                    LOGGER.error("Can't process file {}", s3ObjectKey, ex);
                }

                processedFiles++;
            }
        }
    }

    private static void printStats() {
        long endTime = System.currentTimeMillis();
        long processingTime = (endTime - startTime)/1000;
        long processingRate = processedRecords/processingTime;

        LOGGER.info("Processed {} files, {} records in {} seconds at a rate of {} records/sec",
                processedFiles, processedRecords, processingTime, processingRate);
    }

    private static void initSinkConnection() {
        if (kafkaPersist) {
            initKafkaConnection();
        }

        if (esPersist) {
            initEsConnection();
        }
    }

    private static void initKafkaConnection() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(producerProps);
    }

    private static void initEsConnection() {
        esClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(esServer, Integer.parseInt(esPort), esScheme)
                )
        );

        esListener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                LOGGER.debug("Successfully published bulk request to Elasticsearch: {}",
                        bulkItemResponses.buildFailureMessage());
            }

            @Override
            public void onFailure(Exception e) {
                LOGGER.error("Error sending bulk request to Elasticsearch", e);
            }
        };

        esBulkRequest = new BulkRequest();
    }

    private static void publishRecordToSink(JsonNode record) {
        if (kafkaPersist) {
            publishRecordToKafka(record);
        }

        if (esPersist) {
            publishRecordToEs(record);
        }
    }

    private static void publishRecordToKafka(JsonNode record) {
        ProducerRecord<String, JsonNode> rec = new ProducerRecord<>(kafkaTopic, record);

        if (LOGGER.isDebugEnabled()) {
            producer.send(rec, (recordMetadata, e) -> {
                if (e == null) {
                    LOGGER.debug("Successfully published to Kafka as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {
                    LOGGER.error("Error sending record to Kafka", e);
                }
            });
        } else {
            producer.send(rec);
        }
    }

    private static void publishRecordToEs(JsonNode record) {
        // Add individual JSON records to the Elasticsearch Bulk Request
        IndexRequest esIndexRequest = new IndexRequest(esIndex);
        esIndexRequest.source(record.toString(), XContentType.JSON);
        esBulkRequest.add(esIndexRequest);
    }

    private static void flushRecordstoEs() {
        // Execute the Elasticsearch Bulk Request asynchronously and initialise a new empty Bulk Request object
        esClient.bulkAsync(esBulkRequest, RequestOptions.DEFAULT, esListener);
        esBulkRequest = new BulkRequest();
    }

    // Add the normalised timestamps to the record
    // These consist of timestamps adjusted to the start of the previous hour, day and week
    // to be used as aggregation keys
    private static void addTimestamps(JsonNode record, long timestamp) {
        Date timestampDate = new Date(timestamp);
        ((ObjectNode)record).put("timestamp_str", simpleDataFormat.format(timestampDate));

        long hourly_timestamp = timestamp - (timestamp % MILLIS_IN_HOUR);
        ((ObjectNode)record).put("hourly_timestamp", hourly_timestamp/1000);
        Date hourlyTimestampDate = new Date(hourly_timestamp);
        ((ObjectNode)record).put("hourly_timestamp_str", simpleDataFormat.format(hourlyTimestampDate));

        long daily_timestamp = timestamp - (timestamp % MILLIS_IN_DAY);
        ((ObjectNode)record).put("daily_timestamp", daily_timestamp/1000);
        Date dailyTimestampDate = new Date(daily_timestamp);
        ((ObjectNode)record).put("daily_timestamp_str", simpleDataFormat.format(dailyTimestampDate));

        long weekly_timestamp = timestamp - (timestamp % MILLIS_IN_WEEK);
        ((ObjectNode)record).put("weekly_timestamp", weekly_timestamp/1000);
        Date weeklyTimestampDate = new Date(weekly_timestamp);
        ((ObjectNode)record).put("weekly_timestamp_str", simpleDataFormat.format(weeklyTimestampDate));
    }

    private static void closeSinkConnection() {
        if (kafkaPersist) {
            closeKafkaConnection();
        }

        if (esPersist) {
            closeEsConnection();
        }
    }

    private static void closeKafkaConnection() {
        if (producer!= null)
            producer.close();
        producer = null;
    }

    private static void closeEsConnection() {
        try {
            esClient.close();
        } catch (IOException e) {
            LOGGER.error("Error closing Elasticsearch client", e);
        }
    }

    // Schema class used for CSV parsing and JSON string production
    private static class TelecomRecord {
        public String cell_id;
        public long timestamp;
        public String area_code;
        public double calls_in;
        public double calls_out;
        public double sms_in;
        public double sms_out;
        public double internet_activity;

        @Override
        public String toString() {
            return "TelecomRecord{" +
                    "cell_id='" + cell_id + '\'' +
                    ", timestamp=" + timestamp +
                    ", area_code=" + area_code +
                    ", calls_in=" + calls_in +
                    ", calls_out=" + calls_out +
                    ", sms_in=" + sms_in +
                    ", sms_out=" + sms_out +
                    ", internet_activity=" + internet_activity +
                    '}';
        }
    }
}

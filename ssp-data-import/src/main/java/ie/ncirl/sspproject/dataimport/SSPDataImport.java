package ie.ncirl.sspproject.dataimport;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class SSPDataImport {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSPDataImport.class);

    private static final Properties prop = new Properties();
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String AWS_BUCKET_NAME = "aws.bucketname";
    private static final String AWS_OBJECT_PREFIX = "aws.object.prefix";
    private static final String KAFKA_SERVER = "kafka.server";
    private static final String KAFKA_TOPIC = "kafka.topic";

    private static String bucketName = null;
    private static String objectPrefix = null;
    private static final Region region = Region.EU_WEST_1;

    private static Producer<String, String> producer = null;
    private static String kafkaServer = null;
    private static String kafkaTopic = null;

    private static long startTime = 0;
    private static int processedFiles = 0;
    private static int processedRecords = 0;

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();

        loadProperties();

        initKafkaConnection();

        processAWSObjects();

        closeKafkaConnection();

        printStats();
    }

    private static void loadProperties() {
        try {
            prop.load(Objects.requireNonNull(SSPDataImport.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)));

            bucketName = prop.getProperty(AWS_BUCKET_NAME);
            objectPrefix = prop.getProperty(AWS_OBJECT_PREFIX);
            kafkaServer = prop.getProperty(KAFKA_SERVER);
            kafkaTopic = prop.getProperty(KAFKA_TOPIC);
        } catch (IOException e) {
            LOGGER.error("Unable to load config.properties from classpath");
        }
    }

    private static void processAWSObjects() {
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

                    BufferedReader reader = new BufferedReader(new InputStreamReader(result));

                    String line;
                    int lineNum = 0;
                    while ((line = reader.readLine()) != null) {
                        publishRecordToKafka(kafkaTopic, line);
                        lineNum++;
                    }

                    long fileProcessingTime = (System.currentTimeMillis() - fileStartTime)/1000;

                    LOGGER.info("Processed {} of size {} lines {} in {} secs at a rate of {} records/sec",
                            s3ObjectKey, s3Object.size(), lineNum, fileProcessingTime, lineNum/fileProcessingTime);

                    processedRecords += lineNum;
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

    private static void initKafkaConnection() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        producer = new KafkaProducer<>(producerProps);
    }

    private static void publishRecordToKafka(String topic, String record) {
        ProducerRecord<String, String> rec = new ProducerRecord<>(topic, record);

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

    private static void closeKafkaConnection() {
        if (producer!= null)
            producer.close();
        producer = null;
    }
}

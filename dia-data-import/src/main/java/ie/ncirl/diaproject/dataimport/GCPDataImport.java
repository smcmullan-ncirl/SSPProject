package ie.ncirl.diaproject.dataimport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GCPDataImport {
    private static Logger logger = LoggerFactory.getLogger(GCPDataImport.class);

    private static Producer<String, JsonNode> producer = null;
    private static ObjectMapper objectMapper = null;

    private static Connection dbConn = null;
    private static Statement st = null;
    private static ResultSet rs = null;

    private static int processedFiles = 0;
    private static int processedRecords = 0;
    private static int discardedRecords = 0;

    private static final String bucketName = "openmobiledata_public";
    private static final String destFileDir = "/tmp";
    private static final String kafkaServer = "localhost:9092";
    private static final String kafkaTopic = "my-topic";
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/diadb";
    private static final String dbUser = "diauser";
    private static final String dbPassword = "diapasswd*";

    public static void main(String[] args) {
        initKafkaConnection();

        initDbConnection();

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Bucket bucket = storage.get(bucketName);
        Page<Blob> blobs = bucket.list();

        long startTime = System.currentTimeMillis();

        for (Blob blob : blobs.iterateAll()) {
            String blobName = blob.getName();
            logger.info("Processing {}", blobName);
            Path destBlobFilePath = Paths.get(destFileDir, blobName);
            blob.downloadTo(destBlobFilePath);

            File zipFile = destBlobFilePath.toFile();

            try {
                byte[] buffer = new byte[1024];
                ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
                ZipEntry zipEntry = zis.getNextEntry();

                while (zipEntry != null) {
                    File measurementFile = new File(destFileDir,
                            blobName.substring(0, blobName.lastIndexOf("zip")) + "json");
                    FileOutputStream fos = new FileOutputStream(measurementFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();

                    publishFile(measurementFile);

                    if (!measurementFile.delete()) {
                        logger.warn("Could not delete measurement file {}", measurementFile.getName());
                    }

                    zipEntry = zis.getNextEntry();
                }

                zis.closeEntry();
                zis.close();
            } catch (IOException e) {
                logger.error("Can't process {}: {}", blobName, e);
            }

            if (!zipFile.delete()) {
                logger.warn("Could not delete zip file {}", zipFile.getName());
            }

            processedFiles++;
        }

        long endTime = System.currentTimeMillis();
        long processingTime = (endTime - startTime)/1000;
        long processingRate = (processedRecords + discardedRecords)/processingTime;

        logger.info("Processed {} files, {} records, {} discarded records in {} seconds at a rate of {} records/sec",
                processedFiles, processedRecords, discardedRecords, processingTime, processingRate);

        try {
            if (rs != null && !rs.isClosed())
                rs.close();
            if (st != null && !st.isClosed())
                st.close();
            if  (dbConn != null && !dbConn.isClosed())
                dbConn.close();
        } catch (SQLException e) {
            logger.error("Can't close connection to db", e);
        }

        if (producer!= null)
            producer.close();
    }

    private static void initKafkaConnection() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(props);
        objectMapper = new ObjectMapper();
    }

    private static void initDbConnection() {
        try {
            dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            st = dbConn.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                logger.info("Connect to db version {}", rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error("Cannot connect to db", e);
        }
    }

    private static void publishFile(File measurementFile) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(measurementFile);

        if (jsonNode.isObject()) {
            logger.error("Unsupported operation: Node is Object");
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;

            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode elementNode = arrayNode.get(i);
                publishRecord(elementNode);
            }
        } else if (jsonNode.isValueNode()) {
            logger.error("Unsupported operation: Node is Value");
        }
    }

    private static void publishRecord(JsonNode jsonNode) {
        // Publish record to Kafka
        publishRecordToKafka(jsonNode);

        // Publish record to Postgres
        publishRecordToDb(jsonNode);
    }

    private static void publishRecordToKafka(JsonNode jsonNode) {
        // Publish record to Kafka
        ProducerRecord<String, JsonNode> rec = new ProducerRecord<>(kafkaTopic, jsonNode);

        if (logger.isDebugEnabled()) {
            producer.send(rec, (recordMetadata, e) -> {
                if (e == null) {
                    logger.debug("Successfully published to Kafka as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                    processedRecords++;
                } else {
                    logger.error("Error sending record to Kafka", e);
                    discardedRecords++;
                }
            });
        } else {
            producer.send(rec);
        }
    }

    private static void publishRecordToDb(JsonNode jsonNode) {

    }
}

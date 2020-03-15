package ie.ncirl.diaproject.dataimport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GCPDataImport {
    private static Logger logger = LoggerFactory.getLogger(GCPDataImport.class);

    private static Properties prop = new Properties();
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String GCP_BUCKET_NAME = "gcp.bucketname";
    private static final String TEMP_FILE_DIR = "tempfile.dir";
    private static final String KAFKA_ENABLED = "kafka.enabled";
    private static final String KAFKA_SERVER = "kafka.server";
    private static final String KAFKA_TOPIC = "kafka.topic";
    private static final String DB_ENABLED = "db.enabled";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String TSV_ENABLED = "tsv.enabled";
    private static final String TSV_FILE = "tsv.file";

    private static String tempFileDir = null;

    private static boolean kafkaEnabled = false;
    private static boolean dbEnabled = false;
    private static boolean tsvEnabled = false;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Producer<String, JsonNode> producer = null;
    private static String topic = null;

    private static final String dbQuery = "INSERT INTO authors(id, name) VALUES(?, ?)";
    private static Connection dbConn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;

    private static File tsvFile = null;
    private static BufferedWriter tsvWriter = null;

    private static int processedFiles = 0;
    private static int processedRecords = 0;

    public static void main(String[] args) {
        loadProperties();

        init();

        long startTime = System.currentTimeMillis();

        processGCPBlobs();

        long endTime = System.currentTimeMillis();
        long processingTime = (endTime - startTime)/1000;
        long processingRate = processedRecords/processingTime;

        logger.info("Processed {} files, {} records in {} seconds at a rate of {} records/sec",
                processedFiles, processedRecords, processingTime, processingRate);

        shutdown();
    }

    private static void loadProperties() {
        try {
            prop.load(Objects.requireNonNull(GCPDataImport.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)));

            tempFileDir = prop.getProperty(TEMP_FILE_DIR, "/tmp");

            if (Boolean.parseBoolean(prop.getProperty(KAFKA_ENABLED, "false")))
                kafkaEnabled = true;

            if (Boolean.parseBoolean(prop.getProperty(DB_ENABLED, "false")))
                dbEnabled = true;

            if (Boolean.parseBoolean(prop.getProperty(TSV_ENABLED, "false")))
                tsvEnabled = true;
        } catch (IOException e) {
           logger.error("Unable to load config.properties from classpath");
        }
    }

    private static void init() {
        if (kafkaEnabled)
            initKafkaConnection();

        if (dbEnabled)
            initDbConnection();

        if (tsvEnabled)
            initTsvFile();
    }

    private static void shutdown() {
        if (dbEnabled)
            closeDbConnection();

        if (kafkaEnabled)
            closeKafkaConnection();

        if (tsvEnabled)
            closeTsvFile();
    }

    private static void initKafkaConnection() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, prop.get(KAFKA_SERVER));
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(producerProps);
        topic = prop.getProperty(KAFKA_TOPIC);
    }

    private static void closeKafkaConnection() {
        if (producer!= null)
            producer.close();
            producer = null;
    }

    private static void initDbConnection() {
        try {
            dbConn = DriverManager.getConnection(
                    prop.getProperty(DB_URL), prop.getProperty(DB_USER), prop.getProperty(DB_PASSWORD));
            pst = dbConn.prepareStatement(dbQuery);

            st = dbConn.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                logger.info("Connected to db version {}", rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error("Cannot connect to db", e);
        }
    }

    private static void closeDbConnection() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                rs = null;
            }

            if (st != null && !st.isClosed()) {
                st.close();
                st = null;
            }

            if (pst != null && !pst.isClosed()) {
                pst.close();
                pst = null;
            }

            if  (dbConn != null && !dbConn.isClosed()) {
                dbConn.close();
                dbConn = null;
            }
        } catch (SQLException e) {
            logger.error("Can't close connection to db", e);
        }
    }

    private static void initTsvFile() {
        tsvFile = new File(prop.getProperty(TSV_FILE, "/tmp/output.tsv"));
        try {
            tsvWriter = new BufferedWriter(new FileWriter(tsvFile));
            tsvWriter.write(Measurement.toHdr());
            tsvWriter.newLine();
        } catch (IOException e) {
            logger.error("Can't open TSV writer", e);
        }
    }

    private static void closeTsvFile() {
        if (tsvWriter != null) {
            try {
                tsvWriter.close();
            } catch (IOException e) {
                logger.error("Can't close CSV writer", e);
            }
        }
    }
    private static void processGCPBlobs() {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        Bucket bucket = storage.get(prop.getProperty(GCP_BUCKET_NAME));
        Page<Blob> blobs = bucket.list();

        for (Blob blob : blobs.iterateAll()) {
            String blobName = blob.getName();
            logger.info("Processing {}", blobName);
            Path destBlobFilePath = Paths.get(tempFileDir, blobName);
            blob.downloadTo(destBlobFilePath);

            File zipFile = destBlobFilePath.toFile();

            processZipFile(zipFile);

            if (!zipFile.delete()) {
                logger.warn("Could not delete zip file {}", zipFile.getName());
            }

            processedFiles++;
        }
    }

    private static void processZipFile(File zipFile) {
        try {
            String zipFileName = zipFile.getName();
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                File measurementFile = new File(tempFileDir,
                        zipFileName.substring(0, zipFileName.lastIndexOf("zip")) + "json");
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
            logger.error("Can't process zip file", e);
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
                processedRecords++;
            }
        } else if (jsonNode.isValueNode()) {
            logger.error("Unsupported operation: Node is Value");
        }
    }

    private static void publishRecord(JsonNode jsonNode) {
        if (kafkaEnabled)
            publishRecordToKafka(jsonNode);

        if (dbEnabled)
            publishRecordToDb(jsonNode);

        if (tsvEnabled)
            publishRecordToCsv(jsonNode);
    }

    private static void publishRecordToKafka(JsonNode jsonNode) {
        // Publish record to Kafka
        ProducerRecord<String, JsonNode> rec = new ProducerRecord<>(topic, jsonNode);

        if (logger.isDebugEnabled()) {
            producer.send(rec, (recordMetadata, e) -> {
                if (e == null) {
                    logger.debug("Successfully published to Kafka as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {
                    logger.error("Error sending record to Kafka", e);
                }
            });
        } else {
            producer.send(rec);
        }
    }

    private static void publishRecordToDb(JsonNode jsonNode) {
        int id = 0;
        String author = "";

        try {
            pst.setInt(1, id);
            pst.setString(2, author);
            pst.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't publish record to DB", e);
        }
    }

    private static void publishRecordToCsv(JsonNode jsonNode) {
        try {
            Measurement measurement = objectMapper.treeToValue(jsonNode, Measurement.class);
            tsvWriter.write(measurement.toTSV());
            tsvWriter.newLine();
        } catch (IOException e) {
            logger.error("Can't write CSV file {} {}", tsvFile.getName(), e.getMessage());
        }
    }
}

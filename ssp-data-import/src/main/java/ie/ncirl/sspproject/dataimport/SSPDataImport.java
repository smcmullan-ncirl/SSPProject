package ie.ncirl.sspproject.dataimport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import ie.ncirl.sspproject.dataimport.measurement.Measurement;
import ie.ncirl.sspproject.dataimport.measurement.batteryinfo.BatteryInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.context.ContextMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.cronethttp.CronetHttpMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.deviceinfo.DeviceInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.dnslookup.DnsLookupMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.http.HttpMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.multipathhttp.MultipathHttpMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.multipathlatency.MultipathLatencyMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.myspeedtestdnslookup.MySpeedtestDnsLookupMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.myspeedtestping.MySpeedtestPingMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.networkinfo.NetworkInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.pageloadtime.PageLoadTimeMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.pageloadtime2.PageLoadTime2Measurement;
import ie.ncirl.sspproject.dataimport.measurement.ping.PingMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.pingtest.PingTestMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.quichttp.QuicHttpMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.rrc.RrcMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.sequential.SequentialMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.siminfo.SimInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.stateinfo.StateInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.tcpthroughput.TcpThroughputMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.traceroute.TracerouteMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.udpburst.UdpBurstMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.usageinfo.UsageInfoMeasurement;
import ie.ncirl.sspproject.dataimport.measurement.video.VideoMeasurement;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SSPDataImport {
    private static final Logger logger = LoggerFactory.getLogger(SSPDataImport.class);

    private static final Properties prop = new Properties();
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String GCP_BUCKET_NAME = "gcp.bucketname";
    private static final String TEMP_FILE_DIR = "tempfile.dir";
    private static final String FILE_OFFSET_MIN = "file.offset.min";
    private static final String FILE_OFFSET_MAX = "file.offset.max";
    private static final String KAFKA_ENABLED = "kafka.enabled";
    private static final String KAFKA_SERVER = "kafka.server";
    private static final String DB_ENABLED = "db.enabled";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_SCHEMA = "db.schema";
    private static final String CSV_ENABLED = "csv.enabled";
    private static final String CSV_FILE = "csv.file.prefix";

    private static String tempFileDir = null;

    private static int fileOffsetMin = 0;
    private static int fileOffsetMax = -1;

    private static boolean kafkaEnabled = false;
    private static boolean dbEnabled = false;
    private static boolean csvEnabled = false;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Producer<String, JsonNode> producer = null;

    private static Connection dbConn = null;
    private static Statement st = null;

    private static Map<String, BufferedWriter> csvWriters = null;

    private static long startTime = 0;
    private static int processedFiles = 0;
    private static int processedRecords = 0;

    private static final String PING = "ping";
    private static final String TRACEROUTE = "traceroute";
    private static final String HTTP = "http";
    private static final String DNS_LOOKUP = "dns_lookup";
    private static final String UDP_BURST = "udp_burst";
    private static final String TCPTHROUGHPUT = "tcpthroughput";
    private static final String CONTEXT = "context";
    private static final String MY_SPEEDTEST_PING = "myspeedtest_ping";
    private static final String MY_SPEEDTEST_DNS_LOOKUP = "myspeedtestdns_lookup";
    private static final String DEVICE_INFO = "device_info";
    private static final String NETWORK_INFO = "network_info";
    private static final String BATTERY_INFO = "battery_info";
    private static final String PING_TEST = "ping_test";
    private static final String SIM_INFO = "sim_info";
    private static final String STATE_INFO = "state_info";
    private static final String USAGE_INFO = "usage_info";
    private static final String RRC = "rrc";
    private static final String PAGE_LOAD_TIME = "PageLoadTime";
    private static final String PAGE_LOAD_TIME_2 = "pageloadtime";
    private static final String VIDEO = "video";
    private static final String SEQUENTIAL = "sequential";
    private static final String QUIC_HTTP = "quic-http";
    private static final String CRONET_HTTP = "cronet-http";
    private static final String MULTIPATH_LATENCY = "multipath_latency";
    private static final String MULTIPATH_HTTP = "multipath_http";

    private static int pingCount = 0;
    private static int tracerouteCount = 0;
    private static int httpCount = 0;
    private static int dnsLookupCount = 0;
    private static int udpBurstCount = 0;
    private static int tcpThroughputCount = 0;
    private static int contextCount = 0;
    private static int mySpeedtestPingCount = 0;
    private static int mySpeedtestDnsLookupCount = 0;
    private static int deviceInfoCount = 0;
    private static int networkInfoCount = 0;
    private static int batteryInfoCount = 0;
    private static int pingTestCount = 0;
    private static int simInfoCount = 0;
    private static int stateInfoCount = 0;
    private static int usageInfoCount = 0;
    private static int rrcCount = 0;
    private static int pageLoadTimeCount = 0;
    private static int pageLoadTime2Count = 0;
    private static int videoCount = 0;
    private static int sequentialCount = 0;
    private static int quicHttpCount = 0;
    private static int cronetHttpCount = 0;
    private static int multipathLatencyCount = 0;
    private static int multipathHttpCount = 0;

    public static void main(String[] args) {
        loadProperties();

        init();

        processGCPBlobs();

        printStats();

        shutdown();
    }

    private static void loadProperties() {
        try {
            prop.load(Objects.requireNonNull(SSPDataImport.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)));

            tempFileDir = prop.getProperty(TEMP_FILE_DIR, "/tmp");

            fileOffsetMin = Integer.parseInt(prop.getProperty(FILE_OFFSET_MIN, "0"));
            fileOffsetMax = Integer.parseInt(prop.getProperty(FILE_OFFSET_MAX, "-1"));

            if (Boolean.parseBoolean(prop.getProperty(KAFKA_ENABLED, "false")))
                kafkaEnabled = true;

            if (Boolean.parseBoolean(prop.getProperty(DB_ENABLED, "false")))
                dbEnabled = true;

            if (Boolean.parseBoolean(prop.getProperty(CSV_ENABLED, "false")))
                csvEnabled = true;
        } catch (IOException e) {
            logger.error("Unable to load config.properties from classpath");
        }
    }

    private static void init() {
        startTime = System.currentTimeMillis();

        if (kafkaEnabled)
            initKafkaConnection();

        if (dbEnabled)
            initDbConnection();

        if (csvEnabled)
            initCsvFiles();
    }

    private static void printStats() {
        long endTime = System.currentTimeMillis();
        long processingTime = (endTime - startTime)/1000;
        long processingRate = processedRecords/processingTime;

        logger.info("Processed {} files, {} records in {} seconds at a rate of {} records/sec",
                processedFiles, processedRecords, processingTime, processingRate);

        logger.info("{} : {}", PING, pingCount);
        logger.info("{} : {}", TRACEROUTE, tracerouteCount);
        logger.info("{} : {}", HTTP, httpCount);
        logger.info("{} : {}", DNS_LOOKUP, dnsLookupCount);
        logger.info("{} : {}", UDP_BURST, udpBurstCount);
        logger.info("{} : {}", TCPTHROUGHPUT, tcpThroughputCount);
        logger.info("{} : {}", CONTEXT, contextCount);
        logger.info("{} : {}", MY_SPEEDTEST_PING, mySpeedtestPingCount);
        logger.info("{} : {}", MY_SPEEDTEST_DNS_LOOKUP, mySpeedtestDnsLookupCount);
        logger.info("{} : {}", DEVICE_INFO, deviceInfoCount);
        logger.info("{} : {}", NETWORK_INFO, networkInfoCount);
        logger.info("{} : {}", BATTERY_INFO, batteryInfoCount);
        logger.info("{} : {}", PING_TEST, pingTestCount);
        logger.info("{} : {}", SIM_INFO, simInfoCount);
        logger.info("{} : {}", STATE_INFO, stateInfoCount);
        logger.info("{} : {}", USAGE_INFO, usageInfoCount);
        logger.info("{} : {}", RRC, rrcCount);
        logger.info("{} : {}", PAGE_LOAD_TIME, pageLoadTimeCount);
        logger.info("{} : {}", PAGE_LOAD_TIME_2, pageLoadTime2Count);
        logger.info("{} : {}", VIDEO, videoCount);
        logger.info("{} : {}", SEQUENTIAL, sequentialCount);
        logger.info("{} : {}", QUIC_HTTP, quicHttpCount);
        logger.info("{} : {}", CRONET_HTTP, cronetHttpCount);
        logger.info("{} : {}", MULTIPATH_LATENCY, multipathLatencyCount);
        logger.info("{} : {}", MULTIPATH_HTTP, multipathHttpCount);
    }

    private static void shutdown() {
        if (dbEnabled)
            closeDbConnection();

        if (kafkaEnabled)
            closeKafkaConnection();

        if (csvEnabled)
            closeCsvFiles();
    }

    private static void initKafkaConnection() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, prop.get(KAFKA_SERVER));
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(producerProps);
    }

    private static void closeKafkaConnection() {
        if (producer!= null)
            producer.close();
        producer = null;
    }

    private static void initDbConnection() {
        try {
            dbConn = DriverManager.getConnection(
                    prop.getProperty(DB_URL),
                    prop.getProperty(DB_USER),
                    prop.getProperty(DB_PASSWORD));
            dbConn.setSchema(prop.getProperty(DB_SCHEMA));

            st = dbConn.createStatement();

            ResultSet rs = st.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                logger.info("Connected to db version {}", rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("Cannot connect to db", e);
        }
    }

    private static void closeDbConnection() {
        try {
            if (st != null && !st.isClosed()) {
                st.close();
                st = null;
            }

            if  (dbConn != null && !dbConn.isClosed()) {
                dbConn.close();
                dbConn = null;
            }
        } catch (SQLException e) {
            logger.error("Can't close connection to db", e);
        }
    }

    private static void initCsvFiles() {
        csvWriters = new HashMap<>();
    }

    private static void closeCsvFiles() {
        csvWriters.forEach((topic, writer) -> {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error("Can't close CSV writer for topic {}", topic, e);
            }
        });
    }
    private static void processGCPBlobs() {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        Bucket bucket = storage.get(prop.getProperty(GCP_BUCKET_NAME));
        Page<Blob> blobs = bucket.list();

        for (Blob blob : blobs.iterateAll()) {
            String blobName = blob.getName();

            if(processedFiles >= fileOffsetMin && (fileOffsetMax == -1 || processedFiles < fileOffsetMax) ) {
                logger.info("Processing file {}, records {}, filename {}", processedFiles, processedRecords, blobName);

                Path destBlobFilePath = Paths.get(tempFileDir, blobName);
                blob.downloadTo(destBlobFilePath);

                File zipFile = destBlobFilePath.toFile();

                processZipFile(zipFile);

                if (!zipFile.delete()) {
                    logger.warn("Could not delete zip file {}", zipFile.getName());
                }
            } else {
                logger.info("Skipping file {}, records {}, filename {}", processedFiles, processedRecords, blobName);
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
        String topic = jsonNode.get("type").textValue();

        if (Boolean.parseBoolean(prop.getProperty(topic, "false"))) {
            if (kafkaEnabled)
                publishRecordToKafka(topic, jsonNode);

            if (dbEnabled)
                publishRecordToDb(topic, jsonNode);

            if (csvEnabled)
                publishRecordtoCsv(topic, jsonNode);

            incrementTopicCounters(topic);
        }
    }

    private static void publishRecordToKafka(String topic, JsonNode jsonNode) {
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

    private static void publishRecordToDb(String topic, JsonNode jsonNode) {
        String dbQuery = "";

        try {
            Measurement measurement = getMeasurement(topic, jsonNode);

            dbQuery =
                    "INSERT INTO " + topic + "_measurement ("
                            + measurement.toHdr(Measurement.COMMA)
                            + ") VALUES("
                            + measurement.toCsv(Measurement.NULL, Measurement.SINGLE_QUOTE, Measurement.COMMA) + ")";

            st.executeUpdate(dbQuery);
        } catch (Exception e) {
            logger.info(dbQuery);
            logger.error("Can't publish record to DB for topic {} : {}", topic,
                    e.getMessage() != null ? e.getMessage() : jsonNode, e);
            System.exit(-1);
        }
    }

    private static void publishRecordtoCsv(String topic, JsonNode jsonNode) {
        try {
            Measurement measurement = getMeasurement(topic, jsonNode);

            BufferedWriter csvWriter = csvWriters.get(topic);

            if (csvWriter == null) {
                String filename = prop.getProperty(CSV_FILE, "") + topic + ".csv";
                File csvFile = new File(filename);
                csvWriter = new BufferedWriter(new FileWriter(csvFile));
                csvWriter.write(measurement.toHdr(Measurement.TAB));
                csvWriter.newLine();
                csvWriters.put(topic, csvWriter);
            }

            csvWriter.write(measurement.toCsv(Measurement.NO_NULL, Measurement.NO_QUOTE, Measurement.TAB));
            csvWriter.newLine();
            csvWriter.flush();
        } catch (Exception e) {
            logger.error("Can't write to CSV file for topic {} : {}", topic,
                    e.getMessage() != null ? e.getMessage() : jsonNode, e);
            System.exit(-1);
        }
    }

    private static Measurement getMeasurement(String topic, JsonNode jsonNode) throws Exception {
        Measurement measurement;
        Class measurementClass;

        switch (topic) {
            case PING:
                measurementClass = PingMeasurement.class;
                break;
            case TRACEROUTE:
                measurementClass = TracerouteMeasurement.class;
                break;
            case HTTP:
                measurementClass = HttpMeasurement.class;
                break;
            case DNS_LOOKUP:
                measurementClass = DnsLookupMeasurement.class;
                break;
            case UDP_BURST:
                measurementClass = UdpBurstMeasurement.class;
                break;
            case TCPTHROUGHPUT:
                measurementClass = TcpThroughputMeasurement.class;
                break;
            case CONTEXT:
                measurementClass = ContextMeasurement.class;
                break;
            case MY_SPEEDTEST_PING:
                measurementClass = MySpeedtestPingMeasurement.class;
                break;
            case MY_SPEEDTEST_DNS_LOOKUP:
                measurementClass = MySpeedtestDnsLookupMeasurement.class;
                break;
            case DEVICE_INFO:
                measurementClass = DeviceInfoMeasurement.class;
                break;
            case NETWORK_INFO:
                measurementClass = NetworkInfoMeasurement.class;
                break;
            case BATTERY_INFO:
                measurementClass = BatteryInfoMeasurement.class;
                break;
            case PING_TEST:
                measurementClass = PingTestMeasurement.class;
                break;
            case SIM_INFO:
                measurementClass = SimInfoMeasurement.class;
                break;
            case STATE_INFO:
                measurementClass = StateInfoMeasurement.class;
                break;
            case USAGE_INFO:
                measurementClass = UsageInfoMeasurement.class;
                break;
            case RRC:
                measurementClass = RrcMeasurement.class;
                break;
            case PAGE_LOAD_TIME:
                measurementClass = PageLoadTimeMeasurement.class;
                break;
            case PAGE_LOAD_TIME_2:
                measurementClass = PageLoadTime2Measurement.class;
                break;
            case VIDEO:
                measurementClass = VideoMeasurement.class;
                break;
            case SEQUENTIAL:
                measurementClass = SequentialMeasurement.class;
                break;
            case QUIC_HTTP:
                measurementClass = QuicHttpMeasurement.class;
                break;
            case CRONET_HTTP:
                measurementClass = CronetHttpMeasurement.class;
                break;
            case MULTIPATH_LATENCY:
                measurementClass = MultipathLatencyMeasurement.class;
                break;
            case MULTIPATH_HTTP:
                measurementClass = MultipathHttpMeasurement.class;
                break;
            default:
                throw new Exception("Unsupported Topic: " + topic);
        }

        measurement = (Measurement) objectMapper.treeToValue(jsonNode, measurementClass);
        return measurement;
    }

    private static void incrementTopicCounters(String topic) {
        switch (topic) {
            case PING:
                pingCount++;
                break;
            case TRACEROUTE:
                tracerouteCount++;
                break;
            case HTTP:
                httpCount++;
                break;
            case DNS_LOOKUP:
                dnsLookupCount++;
                break;
            case UDP_BURST:
                udpBurstCount++;
                break;
            case TCPTHROUGHPUT:
                tcpThroughputCount++;
                break;
            case CONTEXT:
                contextCount++;
                break;
            case MY_SPEEDTEST_PING:
                mySpeedtestPingCount++;
                break;
            case MY_SPEEDTEST_DNS_LOOKUP:
                mySpeedtestDnsLookupCount++;
                break;
            case DEVICE_INFO:
                deviceInfoCount++;
                break;
            case NETWORK_INFO:
                networkInfoCount++;
                break;
            case BATTERY_INFO:
                batteryInfoCount++;
                break;
            case PING_TEST:
                pingTestCount++;
                break;
            case SIM_INFO:
                simInfoCount++;
                break;
            case STATE_INFO:
                stateInfoCount++;
                break;
            case USAGE_INFO:
                usageInfoCount++;
                break;
            case RRC:
                rrcCount++;
                break;
            case PAGE_LOAD_TIME:
                pageLoadTimeCount++;
                break;
            case PAGE_LOAD_TIME_2:
                pageLoadTime2Count++;
                break;
            case VIDEO:
                videoCount++;
                break;
            case SEQUENTIAL:
                sequentialCount++;
                break;
            case QUIC_HTTP:
                quicHttpCount++;
                break;
            case CRONET_HTTP:
                cronetHttpCount++;
                break;
            case MULTIPATH_LATENCY:
                multipathLatencyCount++;
                break;
            case MULTIPATH_HTTP:
                multipathHttpCount++;
                break;
            default:
                logger.error("Unsupported Topic: {}", topic);
        }
    }
}

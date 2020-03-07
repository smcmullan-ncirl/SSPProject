package ie.ncirl.diaproject.dataimport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GCPTest {
    private static Logger logger = LoggerFactory.getLogger(GCPTest.class);

    public static void main(String[] args) {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        String bucketName = "openmobiledata_public";
        String destFileDir = "/tmp";

        Bucket bucket = storage.get(bucketName);
        Page<Blob> blobs = bucket.list();

        int items = 0;

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
                    File measurementFile = new File(destFileDir, blobName.substring(0, blobName.lastIndexOf("zip")) + "json");
                    FileOutputStream fos = new FileOutputStream(measurementFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();

                    publishToKafka(measurementFile);

                    measurementFile.delete();

                    zipEntry = zis.getNextEntry();
                }

                zis.closeEntry();
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            zipFile.delete();
            items++;
        }

        logger.info("Processed {} items", items);
    }

    private static void publishToKafka(File measurementFile) throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", ":9094");
        props.put("max.request.size configuration", "4194304");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        Producer<String, JsonNode> producer = new KafkaProducer<>(props);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(measurementFile);

        ProducerRecord<String, JsonNode> rec = new ProducerRecord<String, JsonNode>("my-topic", jsonNode);
        producer.send(rec, (recordMetadata, e) -> {
            if (e== null) {
                logger.info("Successfully published to Kafka as: \n" +
                        "Topic:" + recordMetadata.topic() + "\n" +
                        "Partition:" + recordMetadata.partition() + "\n" +
                        "Offset" + recordMetadata.offset() + "\n" +
                        "Timestamp" + recordMetadata.timestamp());
            } else {
                logger.error("Error sending record to Kafka", e);
            }
        });

        producer.close();
    }

}

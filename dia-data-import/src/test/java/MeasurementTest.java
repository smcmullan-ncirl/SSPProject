import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.ncirl.diaproject.dataimport.Measurement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MeasurementTest {
    private static ObjectMapper objectMapper = null;
    private static File csvFile = null;

    @TempDir
    static File tempDir;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        csvFile = new File(tempDir, "output.csv");
    }

    @Test
    @DisplayName("Parse measurement JSON")
    void parseMeasurementJSON() {
        assertDoesNotThrow(() -> {
            InputStream measurementFile = getClass().getResourceAsStream("test_measurement.json");
            JsonNode jsonNode = objectMapper.readTree(measurementFile);
            Measurement measurement = objectMapper.treeToValue(jsonNode, Measurement.class);

            System.out.println(measurement.toHdr());
            System.out.println(measurement.toTSV());
        });
    }

    @AfterAll
    static void shutdown() {
    }
}

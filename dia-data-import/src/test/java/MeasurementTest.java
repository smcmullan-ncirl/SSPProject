import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ie.ncirl.diaproject.dataimport.Measurement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementTest {
    private static ObjectMapper objectMapper = null;
    private static CsvMapper csvMapper = null;
    private static CsvSchema csvSchema = null;
    private static File csvFile = null;

    @TempDir
    static File tempDir;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        csvMapper = new CsvMapper();
        csvSchema = csvMapper.schemaFor(Measurement.class).withoutHeader();
        csvFile = new File(tempDir, "output.csv");
    }

    @Test
    @DisplayName("Parse measurement JSON")
    void parseMeasurementJSON() {
        assertDoesNotThrow(() -> {
            InputStream measurementFile = getClass().getResourceAsStream("test_measurement.json");
            JsonNode jsonNode = objectMapper.readTree(measurementFile);
            Measurement measurement = objectMapper.treeToValue(jsonNode, Measurement.class);

            ObjectWriter writer = csvMapper.writer(csvSchema);
            OutputStream outstream = new FileOutputStream(csvFile , true);
            writer.writeValue(outstream, measurement);
        });
    }

    @AfterAll
    static void shutdown() {

    }
}

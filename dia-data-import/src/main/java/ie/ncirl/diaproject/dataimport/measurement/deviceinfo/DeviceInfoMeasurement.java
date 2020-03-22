package ie.ncirl.diaproject.dataimport.measurement.deviceinfo;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceInfoMeasurement extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(DeviceInfoMeasurement.class);

    @Override
    public String toHdr(String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

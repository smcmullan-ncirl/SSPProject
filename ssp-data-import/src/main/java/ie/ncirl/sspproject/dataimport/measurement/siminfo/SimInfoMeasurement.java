package ie.ncirl.sspproject.dataimport.measurement.siminfo;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class SimInfoMeasurement extends Measurement {

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

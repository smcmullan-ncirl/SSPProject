package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class HttpMeasurement extends Measurement {
    @Override
    public String toHdr() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toTsv() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

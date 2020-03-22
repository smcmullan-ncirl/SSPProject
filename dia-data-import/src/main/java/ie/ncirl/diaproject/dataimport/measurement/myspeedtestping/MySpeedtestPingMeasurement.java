package ie.ncirl.diaproject.dataimport.measurement.myspeedtestping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class MySpeedtestPingMeasurement extends ie.ncirl.diaproject.dataimport.measurement.Measurement {
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

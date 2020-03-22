package ie.ncirl.diaproject.dataimport.measurement.udpburst;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class UdpBurstMeasurement extends Measurement {
    @Override
    public String toHdr(String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toCsv(String quote, String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toNullCsv(String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

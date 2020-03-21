package ie.ncirl.diaproject.dataimport.measurement.dnslookup;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class DnsLookupMeasurement extends Measurement {
    @Override
    public String toHdr(String sep) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toCsv(String sep, String quote) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

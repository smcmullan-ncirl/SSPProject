package ie.ncirl.diaproject.dataimport.measurement.dnslookup;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DnsLookupMeasurement extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(DnsLookupMeasurement.class);

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

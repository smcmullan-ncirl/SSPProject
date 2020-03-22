package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Location extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(Location.class);

    public String latitude;
    public String longitude;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "loc_latitude", sep);
        separate(sb, "loc_longitude", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, latitude, quote, sep);
        quoteAndSeparate(sb, longitude, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // latitude
        separate(sb, nullValue, NO_SEP); // longitude
        return(sb.toString());
    }
}

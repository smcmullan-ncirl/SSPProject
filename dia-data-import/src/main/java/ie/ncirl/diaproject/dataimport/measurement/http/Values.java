package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Values extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(Values.class);

    public String body;
    public String code;
    public String MeasurementLongitude;
    public String body_len;
    public String CalledByLocation;
    public String time_ms;
    public String MeasurementLatitude;
    public String headers;
    public String headers_len;
    public String error;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_body", sep);
        separate(sb, "v_code", sep);
        separate(sb, "v_MeasurementLongitude", sep);
        separate(sb, "v_body_len", sep);
        separate(sb, "v_CalledByLocation", sep);
        separate(sb, "v_time_ms", sep);
        separate(sb, "v_MeasurementLatitude", sep);
        separate(sb, "v_headers", sep);
        separate(sb, "v_headers_len", sep);
        separate(sb, "v_error", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, body, quote, sep);
        quoteAndSeparate(sb, code, quote, sep);
        quoteAndSeparate(sb, MeasurementLongitude, quote, sep);
        quoteAndSeparate(sb, body_len, quote, sep);
        quoteAndSeparate(sb, CalledByLocation, quote, sep);
        quoteAndSeparate(sb, time_ms, quote, sep);
        quoteAndSeparate(sb, MeasurementLatitude, quote, sep);
        quoteAndSeparate(sb, headers, quote, sep);
        quoteAndSeparate(sb, headers_len, quote, sep);
        quoteAndSeparate(sb, error, quote, NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // body
        separate(sb, nullValue, sep); // code
        separate(sb, nullValue, sep); // MeasurementLongitude
        separate(sb, nullValue, sep); // body_len
        separate(sb, nullValue, sep); // CalledByLocation
        separate(sb, nullValue, sep); // time_ms
        separate(sb, nullValue, sep); // MeasurementLatitude
        separate(sb, nullValue, sep); // headers
        separate(sb, nullValue, sep); // headers_len
        separate(sb, nullValue, NO_SEP); // error
        return(sb.toString());
    }
}

package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parameters extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(Parameters.class);

    public String body;
    public String count;
    public String parameters;
    public String url;
    public String start_time;
    public String interval_sec;
    public String priority;
    public String headers;
    public String end_time;
    public String key;
    public String type;
    public String method;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_body", sep);
        separate(sb, "p_count", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_url", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_headers", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_method", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, body, quote, sep);
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, url, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, headers, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, method, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // body
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // url
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // headers
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, NO_SEP); // method
        return(sb.toString());
    }
}

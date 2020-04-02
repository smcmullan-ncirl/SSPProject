package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskParameters extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(TaskParameters.class);

    public String url;
    public String headers;
    public String method;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_url", sep);
        separate(sb, "tp_headers", sep);
        separate(sb, "tp_method", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, url, quote, sep);
        quoteAndSeparate(sb, headers, quote, sep);
        quoteAndSeparate(sb, method, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // url
        separate(sb, nullValue, sep); // headers
        separate(sb, nullValue, NO_SEP); // method
        return(sb.toString());
    }
}

package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskParameters extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(TaskParameters.class);

    public String url;
    public String headers;
    public String method;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_url", sep);
        separate(sb, "tp_headers", sep);
        separate(sb, "tp_method", sep);
        separate(sb, "tp_profile_1_freq", sep);
        separate(sb, "tp_profile_2_freq", sep);
        separate(sb, "tp_profile_3_freq", sep);
        separate(sb, "tp_profile_4_freq", sep);
        separate(sb, "tp_profile_unlimited", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, url, quote, sep);
        quoteAndSeparate(sb, headers, quote, sep);
        quoteAndSeparate(sb, method, quote, sep);
        quoteAndSeparate(sb, profile_1_freq, quote, sep);
        quoteAndSeparate(sb, profile_2_freq, quote, sep);
        quoteAndSeparate(sb, profile_3_freq, quote, sep);
        quoteAndSeparate(sb, profile_4_freq, quote, sep);
        quoteAndSeparate(sb, profile_unlimited, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // url
        separate(sb, nullValue, sep); // headers
        separate(sb, nullValue, sep); // method
        separate(sb, nullValue, sep); // profile_1_freq
        separate(sb, nullValue, sep); // profile_2_freq
        separate(sb, nullValue, sep); // profile_3_freq
        separate(sb, nullValue, sep); // profile_4_freq
        separate(sb, nullValue, NO_SEP); // profile_unlimited
        return(sb.toString());
    }
}

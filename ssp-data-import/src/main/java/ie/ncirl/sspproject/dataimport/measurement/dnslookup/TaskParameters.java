package ie.ncirl.sspproject.dataimport.measurement.dnslookup;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class TaskParameters extends Measurement {
    public String target;
    public String server;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_target", sep);
        separate(sb, "tp_server", sep);
        separate(sb, "tp_profile_1_freq", sep);
        separate(sb, "tp_profile_2_freq", sep);
        separate(sb, "tp_profile_3_freq", sep);
        separate(sb, "tp_profile_4_freq", sep);
        separate(sb, "tp_profile_unlimited", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, server, quote, sep);
        quoteAndSeparate(sb, profile_1_freq, quote, sep);
        quoteAndSeparate(sb, profile_2_freq, quote, sep);
        quoteAndSeparate(sb, profile_3_freq, quote, sep);
        quoteAndSeparate(sb, profile_4_freq, quote, sep);
        quoteAndSeparate(sb, profile_unlimited, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // server
        separate(sb, nullValue, sep); // profile_1_freq
        separate(sb, nullValue, sep); // profile_2_freq
        separate(sb, nullValue, sep); // profile_3_freq
        separate(sb, nullValue, sep); // profile_4_freq
        separate(sb, nullValue, NO_SEP); // profile_unlimited
        return(sb.toString());
    }
}

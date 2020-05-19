package ie.ncirl.sspproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class TaskParameters extends Measurement {

    public String duration_period_sec;
    public String data_limit_mb_down;
    public String target;
    public String data_limit_mb_up;
    public String dir_up;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;
    public String context_interval_sec;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_duration_period_sec", sep);
        separate(sb, "tp_data_limit_mb_down", sep);
        separate(sb, "tp_target", sep);
        separate(sb, "tp_data_limit_mb_up", sep);
        separate(sb, "tp_dir_up", sep);
        separate(sb, "tp_profile_1_freq", sep);
        separate(sb, "tp_profile_2_freq", sep);
        separate(sb, "tp_profile_3_freq", sep);
        separate(sb, "tp_profile_4_freq", sep);
        separate(sb, "tp_profile_unlimited", sep);
        separate(sb, "tp_context_interval_sec", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, duration_period_sec, quote, sep);
        quoteAndSeparate(sb, data_limit_mb_down, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, data_limit_mb_up, quote, sep);
        quoteAndSeparate(sb, dir_up, quote, sep);
        quoteAndSeparate(sb, profile_1_freq, quote, sep);
        quoteAndSeparate(sb, profile_2_freq, quote, sep);
        quoteAndSeparate(sb, profile_3_freq, quote, sep);
        quoteAndSeparate(sb, profile_4_freq, quote, sep);
        quoteAndSeparate(sb, profile_unlimited, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // duration_period_sec
        separate(sb, nullValue, sep); // data_limit_mb_down
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // data_limit_mb_up
        separate(sb, nullValue, sep); // dir_up
        separate(sb, nullValue, sep); // profile_1_freq
        separate(sb, nullValue, sep); // profile_2_freq
        separate(sb, nullValue, sep); // profile_3_freq
        separate(sb, nullValue, sep); // profile_4_freq
        separate(sb, nullValue, sep); // profile_unlimited
        separate(sb, nullValue, NO_SEP); // context_interval_sec
        return(sb.toString());
    }
}

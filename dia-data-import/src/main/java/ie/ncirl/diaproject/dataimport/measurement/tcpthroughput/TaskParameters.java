package ie.ncirl.diaproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

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
        return null;
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        return null;
    }
}

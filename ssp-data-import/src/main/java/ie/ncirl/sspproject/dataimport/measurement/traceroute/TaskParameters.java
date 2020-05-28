package ie.ncirl.sspproject.dataimport.measurement.traceroute;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class TaskParameters extends Measurement {
    public String max_hop_count;
    public String target;
    public String pings_per_hop;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;

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

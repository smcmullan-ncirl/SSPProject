package ie.ncirl.sspproject.dataimport.measurement.traceroute;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Parameters extends Measurement {
    public String count;
    public String ping_timeout_sec;
    public String parameters;
    public String start_time;
    public String ping_interval_sec;
    public String interval_sec;
    public String pings_per_hop;
    public String priority;
    public String packet_size_byte;
    public String ping_exe;
    public String key;
    public String target;
    public String max_hop_count;
    public String type;
    public String end_time;
    public String context_interval_sec;
    public String pre_condition;
    public String parallel_probe_num;

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

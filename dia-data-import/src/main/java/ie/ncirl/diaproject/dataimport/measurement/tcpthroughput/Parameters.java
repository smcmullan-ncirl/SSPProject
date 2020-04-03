package ie.ncirl.diaproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class Parameters extends Measurement {
    public String pkt_size_up_bytes;
    public String count;
    public String key;
    public String parameters;
    public String slow_start_period_ms;
    public String uplink_finish_msg;
    public String start_time;
    public String port_uplink;
    public String interval_sec;
    public String priority;
    public String dir_up;
    public String end_time;
    public String down_data_limit_bytes;
    public String up_data_limit_bytes;
    public String port_downlink;
    public String sample_period_ms;
    public String type;
    public String duration_period_ms;
    public String tcp_timeout_ms;
    public String target;
    public String data_limit_bytes_up;
    public String data_limit_bytes_down;
    public String port_config;
    public String tcp_timeout_sec;
    public String data_limit_mb_up;
    public String duration_period_sec;
    public String data_limit_mb_down;
    public String slow_start_period_sec;
    public String sample_period_sec;
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

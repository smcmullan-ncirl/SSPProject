package ie.ncirl.sspproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

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
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_pkt_size_up_bytes", sep);
        separate(sb, "p_count", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_slow_start_period_ms", sep);
        separate(sb, "p_uplink_finish_msg", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_port_uplink", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_dir_up", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_down_data_limit_bytes", sep);
        separate(sb, "p_up_data_limit_bytes", sep);
        separate(sb, "p_port_downlink", sep);
        separate(sb, "p_sample_period_ms", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_duration_period_ms", sep);
        separate(sb, "p_tcp_timeout_ms", sep);
        separate(sb, "p_target", sep);
        separate(sb, "p_data_limit_bytes_up", sep);
        separate(sb, "p_data_limit_bytes_down", sep);
        separate(sb, "p_port_config", sep);
        separate(sb, "p_tcp_timeout_sec", sep);
        separate(sb, "p_data_limit_mb_up", sep);
        separate(sb, "p_duration_period_sec", sep);
        separate(sb, "p_data_limit_mb_down", sep);
        separate(sb, "p_slow_start_period_sec", sep);
        separate(sb, "p_sample_period_sec", sep);
        separate(sb, "p_context_interval_sec", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, pkt_size_up_bytes, quote, sep);
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, slow_start_period_ms, quote, sep);
        quoteAndSeparate(sb, uplink_finish_msg, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, port_uplink, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, dir_up, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, down_data_limit_bytes, quote, sep);
        quoteAndSeparate(sb, up_data_limit_bytes, quote, sep);
        quoteAndSeparate(sb, port_downlink, quote, sep);
        quoteAndSeparate(sb, sample_period_ms, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, duration_period_ms, quote, sep);
        quoteAndSeparate(sb, tcp_timeout_ms, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, data_limit_bytes_up, quote, sep);
        quoteAndSeparate(sb, data_limit_bytes_down, quote, sep);
        quoteAndSeparate(sb, port_config, quote, sep);
        quoteAndSeparate(sb, tcp_timeout_sec, quote, sep);
        quoteAndSeparate(sb, data_limit_mb_up, quote, sep);
        quoteAndSeparate(sb, duration_period_sec, quote, sep);
        quoteAndSeparate(sb, data_limit_mb_down, quote, sep);
        quoteAndSeparate(sb, slow_start_period_sec, quote, sep);
        quoteAndSeparate(sb, sample_period_sec, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // pkt_size_up_bytes
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // slow_start_period_ms
        separate(sb, nullValue, sep); // uplink_finish_msg
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // port_uplink
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // dir_up
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // down_data_limit_bytes
        separate(sb, nullValue, sep); // up_data_limit_bytes
        separate(sb, nullValue, sep); // port_downlink
        separate(sb, nullValue, sep); // sample_period_ms
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, sep); // duration_period_ms
        separate(sb, nullValue, sep); // tcp_timeout_ms
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // data_limit_bytes_up
        separate(sb, nullValue, sep); // data_limit_bytes_down
        separate(sb, nullValue, sep); // port_config
        separate(sb, nullValue, sep); // tcp_timeout_sec
        separate(sb, nullValue, sep); // data_limit_mb_up
        separate(sb, nullValue, sep); // duration_period_sec
        separate(sb, nullValue, sep); // data_limit_mb_down
        separate(sb, nullValue, sep); // slow_start_period_sec
        separate(sb, nullValue, sep); // sample_period_sec
        separate(sb, nullValue, NO_SEP); // context_interval_sec
        return(sb.toString());
    }
}

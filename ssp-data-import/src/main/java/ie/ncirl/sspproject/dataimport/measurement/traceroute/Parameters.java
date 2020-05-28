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
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_count", sep);
        separate(sb, "p_ping_timeout_sec", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_ping_interval_sec", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_pings_per_hop", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_packet_size_byte", sep);
        separate(sb, "p_ping_exe", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_target", sep);
        separate(sb, "p_max_hop_count", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_context_interval_sec", sep);
        separate(sb, "p_pre_condition", sep);
        separate(sb, "p_parallel_probe_num", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, ping_timeout_sec, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, ping_interval_sec, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, pings_per_hop, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, packet_size_byte, quote, sep);
        quoteAndSeparate(sb, ping_exe, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, max_hop_count, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, sep);
        quoteAndSeparate(sb, pre_condition, quote, sep);
        quoteAndSeparate(sb, parallel_probe_num, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // ping_timeout_sec
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // ping_interval_sec
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // pings_per_hop
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // packet_size_byte
        separate(sb, nullValue, sep); // ping_exe
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // max_hop_count
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // context_interval_sec
        separate(sb, nullValue, sep); // pre_condition
        separate(sb, nullValue, NO_SEP); // parallel_probe_num
        return(sb.toString());
    }
}

package ie.ncirl.sspproject.dataimport.measurement.udpburst;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Parameters extends Measurement {

    public String count;
    public String target;
    public String parameters;
    public String packet_size_byte;
    public String start_time;
    public String interval_sec;
    public String priority;
    public String dir_up;
    public String end_time;
    public String key;
    public String dst_port;
    public String type;
    public String udp_burst_count;
    public String udp_interval;
    public String context_interval_sec;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_count", sep);
        separate(sb, "p_target", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_packet_size_byte", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_dir_up", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_dst_port", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_udp_burst_count", sep);
        separate(sb, "p_udp_interval", sep);
        separate(sb, "p_context_interval_sec", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, packet_size_byte, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, dir_up, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, dst_port, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, udp_burst_count, quote, sep);
        quoteAndSeparate(sb, udp_interval, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // packet_size_byte
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // dir_up
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // dst_port
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, sep); // udp_burst_count
        separate(sb, nullValue, sep); // udp_interval
        separate(sb, nullValue, NO_SEP); // context_interval_sec
        return(sb.toString());
    }
}

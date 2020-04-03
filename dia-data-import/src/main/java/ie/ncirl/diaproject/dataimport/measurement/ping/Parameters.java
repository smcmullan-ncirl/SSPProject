package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class Parameters extends Measurement {

    public String count;
    public String target;
    public String parameters;
    public String start_time;
    public String interval_sec;
    public String ping_timeout_sec;
    public String priority;
    public String packet_size_byte;
    public String ping_exe;
    public String key;
    public String type;
    public String end_time;
    public String myspeedtest;
    public String ping;
    public String options;
    public String context_interval_sec;
    public String ping_time_to_live;
    public String ping_icmp_interval_sec;
    public String sensitive;
    public String ping_method;
    public String use_https;
    public String port;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_count", sep);
        separate(sb, "p_target", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_ping_timeout_sec", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_packet_size_byte", sep);
        separate(sb, "p_ping_exe", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_myspeedtest", sep);
        separate(sb, "p_ping", sep);
        separate(sb, "p_options", sep);
        separate(sb, "p_context_interval_sec", sep);
        separate(sb, "p_ping_time_to_live", sep);
        separate(sb, "p_ping_icmp_interval_sec", sep);
        separate(sb, "p_sensitive", sep);
        separate(sb, "p_ping_method", sep);
        separate(sb, "p_use_https", sep);
        separate(sb, "p_port", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, ping_timeout_sec, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, packet_size_byte, quote, sep);
        quoteAndSeparate(sb, ping_exe, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, myspeedtest, quote, sep);
        quoteAndSeparate(sb, ping, quote, sep);
        quoteAndSeparate(sb, options, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, sep);
        quoteAndSeparate(sb, ping_time_to_live, quote, sep);
        quoteAndSeparate(sb, ping_icmp_interval_sec, quote, sep);
        quoteAndSeparate(sb, sensitive, quote, sep);
        quoteAndSeparate(sb, ping_method, quote, sep);
        quoteAndSeparate(sb, use_https, quote, sep);
        quoteAndSeparate(sb, port, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // ping_timeout_sec
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // packet_size_byte
        separate(sb, nullValue, sep); // ping_exe
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // myspeedtest
        separate(sb, nullValue, sep); // ping
        separate(sb, nullValue, sep); // options
        separate(sb, nullValue, sep); // context_interval_sec
        separate(sb, nullValue, sep); // ping_time_to_live
        separate(sb, nullValue, sep); // ping_icmp_interval_sec
        separate(sb, nullValue, sep); // sensitive
        separate(sb, nullValue, sep); // ping_method
        separate(sb, nullValue, sep); // use_https
        separate(sb, nullValue, NO_SEP); // port
        return(sb.toString());
    }
}

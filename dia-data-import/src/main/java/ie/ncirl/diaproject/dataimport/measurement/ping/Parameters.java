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
    public String toCsv(String quote, String sep) {
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
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep) // count
                .append(sep) // target
                .append(sep) // parameters
                .append(sep) // start_time
                .append(sep) // interval_sec
                .append(sep) // ping_timeout_sec
                .append(sep) // priority
                .append(sep) // packet_size_byte
                .append(sep) // ping_exe
                .append(sep) // key
                .append(sep) // type
                .append(sep) // end_time
                .append(sep) // myspeedtest
                .append(sep) // ping
                .append(sep) // options
                .append(sep) // context_interval_sec
                .append(sep) // ping_time_to_live
                .append(sep) // ping_icmp_interval_sec
                .append(sep) // sensitive
                .append(sep) // ping_method
                .append(sep) // use_https
                .append(NO_SEP); // port
        return(sb.toString());
    }
}

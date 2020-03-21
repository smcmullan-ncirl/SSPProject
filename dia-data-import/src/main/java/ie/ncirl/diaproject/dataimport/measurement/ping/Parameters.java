package ie.ncirl.diaproject.dataimport.measurement.ping;

public class Parameters {
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

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("p_count").append(sep)
                .append("p_target").append(sep)
                .append("p_parameters").append(sep)
                .append("p_start_time").append(sep)
                .append("p_interval_sec").append(sep)
                .append("p_ping_timeout_sec").append(sep)
                .append("p_priority").append(sep)
                .append("p_packet_size_byte").append(sep)
                .append("p_ping_exe").append(sep)
                .append("p_key").append(sep)
                .append("p_type").append(sep)
                .append("p_end_time").append(sep)
                .append("p_myspeedtest").append(sep)
                .append("p_ping").append(sep)
                .append("p_options").append(sep)
                .append("p_context_interval_sec").append(sep)
                .append("p_ping_time_to_live").append(sep)
                .append("p_ping_icmp_interval_sec").append(sep)
                .append("p_sensitive").append(sep)
                .append("p_ping_method").append(sep)
                .append("p_use_https").append(sep)
                .append("p_port");
        return(sb.toString());
    }

    public String toCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(count).append(sep)
                .append(target).append(sep)
                .append(parameters).append(sep)
                .append(start_time).append(sep)
                .append(interval_sec).append(sep)
                .append(ping_timeout_sec).append(sep)
                .append(priority).append(sep)
                .append(packet_size_byte).append(sep)
                .append(ping_exe).append(sep)
                .append(key).append(sep)
                .append(type).append(sep)
                .append(end_time).append(sep)
                .append(myspeedtest).append(sep)
                .append(ping).append(sep)
                .append(options).append(sep)
                .append(context_interval_sec).append(sep)
                .append(ping_time_to_live).append(sep)
                .append(ping_icmp_interval_sec).append(sep)
                .append(sensitive).append(sep)
                .append(ping_method).append(sep)
                .append(use_https).append(sep)
                .append(port);
        return(sb.toString());
    }

}

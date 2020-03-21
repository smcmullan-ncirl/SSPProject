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

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("p_count").append("\t")
                .append("p_target").append("\t")
                .append("p_parameters").append("\t")
                .append("p_start_time").append("\t")
                .append("p_interval_sec").append("\t")
                .append("p_ping_timeout_sec").append("\t")
                .append("p_priority").append("\t")
                .append("p_packet_size_byte").append("\t")
                .append("p_ping_exe").append("\t")
                .append("p_key").append("\t")
                .append("p_type").append("\t")
                .append("p_end_time").append("\t")
                .append("p_myspeedtest").append("\t")
                .append("p_ping").append("\t")
                .append("p_options").append("\t")
                .append("p_context_interval_sec").append("\t")
                .append("p_ping_time_to_live").append("\t")
                .append("p_ping_icmp_interval_sec").append("\t")
                .append("p_sensitive").append("\t")
                .append("p_ping_method").append("\t")
                .append("p_use_https").append("\t")
                .append("p_port");
        return(sb.toString());
    }

    public String toTsv() {
        StringBuffer sb = new StringBuffer()
                .append(count).append("\t")
                .append(target).append("\t")
                .append(parameters).append("\t")
                .append(start_time).append("\t")
                .append(interval_sec).append("\t")
                .append(ping_timeout_sec).append("\t")
                .append(priority).append("\t")
                .append(packet_size_byte).append("\t")
                .append(ping_exe).append("\t")
                .append(key).append("\t")
                .append(type).append("\t")
                .append(end_time).append("\t")
                .append(myspeedtest).append("\t")
                .append(ping).append("\t")
                .append(options).append("\t")
                .append(context_interval_sec).append("\t")
                .append(ping_time_to_live).append("\t")
                .append(ping_icmp_interval_sec).append("\t")
                .append(sensitive).append("\t")
                .append(ping_method).append("\t")
                .append(use_https).append("\t")
                .append(port);
        return(sb.toString());
    }

}

package ie.ncirl.diaproject.dataimport.measurement;

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
    public String server;
    public String pings_per_hop;
    public String ping_interval_sec;
    public String body;
    public String url;
    public String max_hop_count;
    public String headers;
    public String method;
    public String dir_up;
    public String dst_port;
    public String udp_burst_count;
    public String pkt_size_up_bytes;
    public String slow_start_period_ms;
    public String data_limit_bytes_up;
    public String uplink_finish_msg;
    public String port_uplink;
    public String data_limit_bytes_down;
    public String down_data_limit_bytes;
    public String sample_period_ms;
    public String up_data_limit_bytes;
    public String duration_period_ms;
    public String port_downlink;
    public String tcp_timeout_ms;
    public String port_config;
    public String tcp_timeout_sec;
    public String duration_period_sec;
    public String data_limit_mb_up;
    public String data_limit_mb_down;
    public String sample_period_sec;
    public String slow_start_period_sec;
    public String myspeedtest;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("count").append("\t")
                .append("target").append("\t")
                .append("parameters").append("\t")
                .append("start_time").append("\t")
                .append("interval_sec").append("\t")
                .append("ping_timeout_sec").append("\t")
                .append("priority").append("\t")
                .append("packet_size_byte").append("\t")
                .append("ping_exe").append("\t")
                .append("key").append("\t")
                .append("type").append("\t")
                .append("end_time").append("\t")
                .append("server").append("\t")
                .append("pings_per_hop").append("\t")
                .append("ping_interval_sec").append("\t")
                .append("body").append("\t")
                .append("url").append("\t")
                .append("max_hop_count").append("\t")
                .append("headers").append("\t")
                .append("method").append("\t")
                .append("dir_up").append("\t")
                .append("dst_port").append("\t")
                .append("udp_burst_count").append("\t")
                .append("pkt_size_up_bytes").append("\t")
                .append("slow_start_period_ms").append("\t")
                .append("data_limit_bytes_up").append("\t")
                .append("uplink_finish_msg").append("\t")
                .append("port_uplink").append("\t")
                .append("data_limit_bytes_down").append("\t")
                .append("down_data_limit_bytes").append("\t")
                .append("sample_period_ms").append("\t")
                .append("up_data_limit_bytes").append("\t")
                .append("duration_period_ms").append("\t")
                .append("port_downlink").append("\t")
                .append("tcp_timeout_ms").append("\t")
                .append("port_config").append("\t")
                .append("tcp_timeout_sec").append("\t")
                .append("duration_period_sec").append("\t")
                .append("data_limit_mb_up").append("\t")
                .append("data_limit_mb_down").append("\t")
                .append("sample_period_sec").append("\t")
                .append("slow_start_period_sec").append("\t")
                .append("myspeedtest");
        return(sb.toString());
    }

    public String toTSV() {
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
                .append(server).append("\t")
                .append(pings_per_hop).append("\t")
                .append(ping_interval_sec).append("\t")
                .append(body).append("\t")
                .append(url).append("\t")
                .append(max_hop_count).append("\t")
                .append(headers).append("\t")
                .append(method).append("\t")
                .append(dir_up).append("\t")
                .append(dst_port).append("\t")
                .append(udp_burst_count).append("\t")
                .append(pkt_size_up_bytes).append("\t")
                .append(slow_start_period_ms).append("\t")
                .append(data_limit_bytes_up).append("\t")
                .append(uplink_finish_msg).append("\t")
                .append(port_uplink).append("\t")
                .append(data_limit_bytes_down).append("\t")
                .append(down_data_limit_bytes).append("\t")
                .append(sample_period_ms).append("\t")
                .append(up_data_limit_bytes).append("\t")
                .append(duration_period_ms).append("\t")
                .append(port_downlink).append("\t")
                .append(tcp_timeout_ms).append("\t")
                .append(port_config).append("\t")
                .append(tcp_timeout_sec).append("\t")
                .append(duration_period_sec).append("\t")
                .append(data_limit_mb_up).append("\t")
                .append(data_limit_mb_down).append("\t")
                .append(sample_period_sec).append("\t")
                .append(slow_start_period_sec).append("\t")
                .append(myspeedtest);
        return(sb.toString());
    }
}

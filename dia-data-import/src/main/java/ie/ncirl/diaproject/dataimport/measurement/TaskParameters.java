package ie.ncirl.diaproject.dataimport.measurement;

public class TaskParameters {
    public String location_update_distance;
    public String target;
    public String trigger_location_update;
    public String server;
    public String url;
    public String max_hop_count;
    public String packet_size_byte;
    public String pings_per_hop;
    public String ping_timeout_sec;
    public String headers;
    public String method;
    public String duration_period_sec;
    public String data_limit_mb_down;
    public String data_limit_mb_up;
    public String dir_up;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("location_update_distance").append("\t")
                .append("target").append("\t")
                .append("trigger_location_update").append("\t")
                .append("server").append("\t")
                .append("url").append("\t")
                .append("max_hop_count").append("\t")
                .append("packet_size_byte").append("\t")
                .append("pings_per_hop").append("\t")
                .append("ping_timeout_sec").append("\t")
                .append("headers").append("\t")
                .append("method").append("\t")
                .append("duration_period_sec").append("\t")
                .append("data_limit_mb_down").append("\t")
                .append("data_limit_mb_up").append("\t")
                .append("dir_up");
        return(sb.toString());
    }

    public static String toNullTsv() {
        StringBuffer sb = new StringBuffer()
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t");
        return(sb.toString());
    }

    public String toTSV() {
        StringBuffer sb = new StringBuffer()
                .append(location_update_distance).append("\t")
                .append(target).append("\t")
                .append(trigger_location_update).append("\t")
                .append(server).append("\t")
                .append(url).append("\t")
                .append(max_hop_count).append("\t")
                .append(packet_size_byte).append("\t")
                .append(pings_per_hop).append("\t")
                .append(ping_timeout_sec).append("\t")
                .append(headers).append("\t")
                .append(method).append("\t")
                .append(duration_period_sec).append("\t")
                .append(data_limit_mb_down).append("\t")
                .append(data_limit_mb_up).append("\t")
                .append(dir_up);
        return(sb.toString());
    }
}

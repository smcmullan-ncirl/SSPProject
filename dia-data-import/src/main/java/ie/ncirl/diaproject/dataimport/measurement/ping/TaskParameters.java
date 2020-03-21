package ie.ncirl.diaproject.dataimport.measurement.ping;

public class TaskParameters {

    public String location_update_distance;
    public String target;
    public String trigger_location_update;
    public String packet_size_byte;
    public String ping_timeout_sec;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("tp_location_update_distance").append(sep)
                .append("tp_target").append(sep)
                .append("tp_trigger_location_update").append(sep)
                .append("tp_packet_size_byte").append(sep)
                .append("tp_ping_timeout_sec").append(sep)
                .append("tp_profile_1_freq").append(sep)
                .append("tp_profile_2_freq").append(sep)
                .append("tp_profile_3_freq").append(sep)
                .append("tp_profile_4_freq").append(sep)
                .append("tp_profile_unlimited");
        return(sb.toString());
    }

    public static String toNullTsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep);
        return(sb.toString());
    }

    public String toCsv(String sep, String quote) {
        StringBuffer sb = new StringBuffer()
                .append(location_update_distance).append(sep)
                .append(target).append(sep)
                .append(trigger_location_update).append(sep)
                .append(packet_size_byte).append(sep)
                .append(ping_timeout_sec).append(sep)
                .append(profile_1_freq).append(sep)
                .append(profile_2_freq).append(sep)
                .append(profile_3_freq).append(sep)
                .append(profile_4_freq).append(sep)
                .append(profile_unlimited);
        return(sb.toString());
    }

}

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

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("tp_location_update_distance").append("\t")
                .append("tp_target").append("\t")
                .append("tp_trigger_location_update").append("\t")
                .append("tp_packet_size_byte").append("\t")
                .append("tp_ping_timeout_sec").append("\t")
                .append("tp_profile_1_freq").append("\t")
                .append("tp_profile_2_freq").append("\t")
                .append("tp_profile_3_freq").append("\t")
                .append("tp_profile_4_freq").append("\t")
                .append("tp_profile_unlimited");
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
                .append("\t");
        return(sb.toString());
    }

    public String toTsv() {
        StringBuffer sb = new StringBuffer()
                .append(location_update_distance).append("\t")
                .append(target).append("\t")
                .append(trigger_location_update).append("\t")
                .append(packet_size_byte).append("\t")
                .append(ping_timeout_sec).append("\t")
                .append(profile_1_freq).append("\t")
                .append(profile_2_freq).append("\t")
                .append(profile_3_freq).append("\t")
                .append(profile_4_freq).append("\t")
                .append(profile_unlimited);
        return(sb.toString());
    }

}

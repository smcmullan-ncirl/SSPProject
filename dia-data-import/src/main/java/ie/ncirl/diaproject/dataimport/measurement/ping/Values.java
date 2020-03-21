package ie.ncirl.diaproject.dataimport.measurement.ping;

public class Values {

    public String packets_sent;
    public String packet_loss;
    public String mean_rtt_ms;
    public String CalledByLocation;
    public String MeasurementLatitude;
    public String max_rtt_ms;
    public String target_ip;
    public String stddev_rtt_ms;
    public String min_rtt_ms;
    public String MeasurementLongitude;
    public String filtered_mean_rtt_ms;
    public String error;
    public String ping_method;
    public String battery;
    public String src_ip;
    public String dst_ip;
    public String measure;
    public String time;
    public String context_results;
    public String mptcp_config;

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("v_packets_sent").append(sep)
                .append("v_packet_loss").append(sep)
                .append("v_mean_rtt_ms").append(sep)
                .append("v_CalledByLocation").append(sep)
                .append("v_MeasurementLatitude").append(sep)
                .append("v_max_rtt_ms").append(sep)
                .append("v_target_ip").append(sep)
                .append("v_stddev_rtt_ms").append(sep)
                .append("v_min_rtt_ms").append(sep)
                .append("v_MeasurementLongitude").append(sep)
                .append("v_filtered_mean_rtt_ms").append(sep)
                .append("v_error").append(sep)
                .append("v_ping_method").append(sep)
                .append("v_battery").append(sep)
                .append("v_src_ip").append(sep)
                .append("v_dst_ip").append(sep)
                .append("v_measure").append(sep)
                .append("v_time").append(sep)
                .append("v_context_results").append(sep)
                .append("v_mptcp_config");

        return(sb.toString());
    }

    public String toCsv(String sep, String quote) {
        StringBuffer sb = new StringBuffer()
                .append(packets_sent).append(sep)
                .append(packet_loss).append(sep)
                .append(mean_rtt_ms).append(sep)
                .append(CalledByLocation).append(sep)
                .append(MeasurementLatitude).append(sep)
                .append(max_rtt_ms).append(sep)
                .append(target_ip).append(sep)
                .append(stddev_rtt_ms).append(sep)
                .append(min_rtt_ms).append(sep)
                .append(MeasurementLongitude).append(sep)
                .append(filtered_mean_rtt_ms).append(sep)
                .append(error).append(sep)
                .append(ping_method).append(sep)
                .append(battery).append(sep)
                .append(src_ip).append(sep)
                .append(dst_ip).append(sep)
                .append(measure).append(sep)
                .append(time).append(sep)
                .append(context_results).append(sep)
                .append(mptcp_config);

        return(sb.toString());
    }

}

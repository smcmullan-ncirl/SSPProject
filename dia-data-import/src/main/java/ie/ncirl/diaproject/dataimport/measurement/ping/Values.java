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

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("packets_sent").append("\t")
                .append("packet_loss").append("\t")
                .append("mean_rtt_ms").append("\t")
                .append("CalledByLocation").append("\t")
                .append("MeasurementLatitude").append("\t")
                .append("max_rtt_ms").append("\t")
                .append("target_ip").append("\t")
                .append("stddev_rtt_ms").append("\t")
                .append("min_rtt_ms").append("\t")
                .append("MeasurementLongitude").append("\t")
                .append("filtered_mean_rtt_ms").append("\t")
                .append("error").append("\t")
                .append("ping_method").append("\t")
                .append("battery").append("\t")
                .append("src_ip").append("\t")
                .append("dst_ip").append("\t")
                .append("measure").append("\t")
                .append("time").append("\t")
                .append("context_results").append("\t")
                .append("mptcp_config");

        return(sb.toString());
    }

    public String toTsv() {
        StringBuffer sb = new StringBuffer()
                .append(packets_sent).append("\t")
                .append(packet_loss).append("\t")
                .append(mean_rtt_ms).append("\t")
                .append(CalledByLocation).append("\t")
                .append(MeasurementLatitude).append("\t")
                .append(max_rtt_ms).append("\t")
                .append(target_ip).append("\t")
                .append(stddev_rtt_ms).append("\t")
                .append(min_rtt_ms).append("\t")
                .append(MeasurementLongitude).append("\t")
                .append(filtered_mean_rtt_ms).append("\t")
                .append(error).append("\t")
                .append(ping_method).append("\t")
                .append(battery).append("\t")
                .append(src_ip).append("\t")
                .append(dst_ip).append("\t")
                .append(measure).append("\t")
                .append(time).append("\t")
                .append(context_results).append("\t")
                .append(mptcp_config);

        return(sb.toString());
    }

}

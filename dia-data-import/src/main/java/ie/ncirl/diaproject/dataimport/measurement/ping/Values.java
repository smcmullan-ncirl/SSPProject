package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class Values extends Measurement {

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

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_packets_sent", sep);
        separate(sb, "v_packet_loss", sep);
        separate(sb, "v_mean_rtt_ms", sep);
        separate(sb, "v_CalledByLocation", sep);
        separate(sb, "v_MeasurementLatitude", sep);
        separate(sb, "v_max_rtt_ms", sep);
        separate(sb, "v_target_ip", sep);
        separate(sb, "v_stddev_rtt_ms", sep);
        separate(sb, "v_min_rtt_ms", sep);
        separate(sb, "v_MeasurementLongitude", sep);
        separate(sb, "v_filtered_mean_rtt_ms", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_ping_method", sep);
        separate(sb, "v_battery", sep);
        separate(sb, "v_src_ip", sep);
        separate(sb, "v_dst_ip", sep);
        separate(sb, "v_measure", sep);
        separate(sb, "v_time", sep);
        separate(sb, "v_context_results", sep);
        separate(sb, "v_mptcp_config", NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toCsv(String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, packets_sent, quote, sep);
        quoteAndSeparate(sb, packet_loss, quote, sep);
        quoteAndSeparate(sb, mean_rtt_ms, quote, sep);
        quoteAndSeparate(sb, CalledByLocation, quote, sep);
        quoteAndSeparate(sb, MeasurementLatitude, quote, sep);
        quoteAndSeparate(sb, max_rtt_ms, quote, sep);
        quoteAndSeparate(sb, target_ip, quote, sep);
        quoteAndSeparate(sb, stddev_rtt_ms, quote, sep);
        quoteAndSeparate(sb, min_rtt_ms, quote, sep);
        quoteAndSeparate(sb, MeasurementLongitude, quote, sep);
        quoteAndSeparate(sb, filtered_mean_rtt_ms, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, ping_method, quote, sep);
        quoteAndSeparate(sb, battery, quote, sep);
        quoteAndSeparate(sb, src_ip, quote, sep);
        quoteAndSeparate(sb, dst_ip, quote, sep);
        quoteAndSeparate(sb, measure, quote, sep);
        quoteAndSeparate(sb, time, quote, sep);
        quoteAndSeparate(sb, context_results, quote, sep);
        quoteAndSeparate(sb, mptcp_config, quote, NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep) // packets_sent
                .append(sep) // packet_loss
                .append(sep) // mean_rtt_ms
                .append(sep) // CalledByLocation
                .append(sep) // MeasurementLatitude
                .append(sep) // max_rtt_ms
                .append(sep) // target_ip
                .append(sep) // stddev_rtt_ms
                .append(sep) // min_rtt_ms
                .append(sep) // MeasurementLongitude
                .append(sep) // filtered_mean_rtt_ms
                .append(sep) // error
                .append(sep) // ping_method
                .append(sep) // battery
                .append(sep) // src_ip
                .append(sep) // dst_ip
                .append(sep) // measure
                .append(sep) // time
                .append(sep) // context_result
                .append(NO_SEP); // mptcp_config
        return(sb.toString());
    }
}

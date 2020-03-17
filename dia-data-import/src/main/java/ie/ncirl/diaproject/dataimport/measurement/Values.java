package ie.ncirl.diaproject.dataimport.measurement;

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
    public String time_ms;
    public String filtered_mean_rtt_ms;
    public String real_hostname;
    public String address;
    public String error;

    public String hop_0_rtt_ms;
    public String hop_1_rtt_ms;
    public String hop_2_rtt_ms;
    public String hop_3_rtt_ms;
    public String hop_4_rtt_ms;
    public String hop_5_rtt_ms;
    public String hop_6_rtt_ms;
    public String hop_7_rtt_ms;
    public String hop_8_rtt_ms;
    public String hop_9_rtt_ms;
    public String hop_10_rtt_ms;
    public String hop_11_rtt_ms;
    public String hop_12_rtt_ms;
    public String hop_13_rtt_ms;
    public String hop_14_rtt_ms;
    public String hop_15_rtt_ms;
    public String hop_16_rtt_ms;
    public String hop_17_rtt_ms;
    public String hop_18_rtt_ms;
    public String hop_19_rtt_ms;
    public String hop_20_rtt_ms;
    public String hop_21_rtt_ms;
    public String hop_22_rtt_ms;
    public String hop_23_rtt_ms;
    public String hop_24_rtt_ms;
    public String hop_25_rtt_ms;
    public String hop_26_rtt_ms;
    public String hop_27_rtt_ms;
    public String hop_28_rtt_ms;
    public String hop_29_rtt_ms;
    public String hop_30_rtt_ms;

    public String hop_0_rtt_ms_1;

    public String code;
    public String body;

    public String hop_0_addr_1;
    public String hop_1_addr_1;
    public String hop_2_addr_1;
    public String hop_3_addr_1;
    public String hop_4_addr_1;
    public String hop_5_addr_1;
    public String hop_6_addr_1;
    public String hop_7_addr_1;
    public String hop_8_addr_1;
    public String hop_9_addr_1;
    public String hop_10_addr_1;
    public String hop_11_addr_1;
    public String hop_12_addr_1;
    public String hop_13_addr_1;
    public String hop_14_addr_1;
    public String hop_15_addr_1;
    public String hop_16_addr_1;
    public String hop_17_addr_1;
    public String hop_18_addr_1;
    public String hop_19_addr_1;
    public String hop_20_addr_1;
    public String hop_21_addr_1;
    public String hop_22_addr_1;
    public String hop_23_addr_1;
    public String hop_24_addr_1;
    public String hop_25_addr_1;
    public String hop_26_addr_1;
    public String hop_27_addr_1;
    public String hop_28_addr_1;
    public String hop_29_addr_1;
    public String hop_30_addr_1;

    public String hop_0_addr_2;
    public String hop_1_addr_2;
    public String hop_2_addr_2;
    public String hop_3_addr_2;
    public String hop_4_addr_2;
    public String hop_5_addr_2;
    public String hop_6_addr_2;
    public String hop_7_addr_2;
    public String hop_8_addr_2;
    public String hop_9_addr_2;
    public String hop_10_addr_2;
    public String hop_11_addr_2;
    public String hop_12_addr_2;
    public String hop_13_addr_2;
    public String hop_14_addr_2;
    public String hop_15_addr_2;
    public String hop_16_addr_2;
    public String hop_17_addr_2;
    public String hop_18_addr_2;
    public String hop_19_addr_2;
    public String hop_20_addr_2;
    public String hop_21_addr_2;
    public String hop_22_addr_2;
    public String hop_23_addr_2;
    public String hop_24_addr_2;
    public String hop_25_addr_2;
    public String hop_26_addr_2;
    public String hop_27_addr_2;
    public String hop_28_addr_2;
    public String hop_29_addr_2;
    public String hop_30_addr_2;

    public String hop_0_addr_3;
    public String hop_1_addr_3;
    public String hop_2_addr_3;
    public String hop_3_addr_3;
    public String hop_4_addr_3;
    public String hop_5_addr_3;
    public String hop_6_addr_3;
    public String hop_7_addr_3;
    public String hop_8_addr_3;
    public String hop_9_addr_3;
    public String hop_10_addr_3;
    public String hop_11_addr_3;
    public String hop_12_addr_3;
    public String hop_13_addr_3;
    public String hop_14_addr_3;
    public String hop_15_addr_3;
    public String hop_16_addr_3;
    public String hop_17_addr_3;
    public String hop_18_addr_3;
    public String hop_19_addr_3;
    public String hop_20_addr_3;
    public String hop_21_addr_3;
    public String hop_22_addr_3;
    public String hop_23_addr_3;
    public String hop_24_addr_3;
    public String hop_25_addr_3;
    public String hop_26_addr_3;
    public String hop_27_addr_3;
    public String hop_28_addr_3;
    public String hop_29_addr_3;
    public String hop_30_addr_3;

    public String body_len;
    public String headers;
    public String headers_len;
    public String num_hops;
    public String PRR;
    public String throught;
    public String speed;
    public String speedAll;
    public String speedDisplay;
    public String duration;
    public String data_limit_exceeded;
    public String server_version;
    public String tcp_speed_results;
    public String ping_method;
    public String currentBatteryLevel;
    public String incrementMobileSend;
    public String incrementMobileBytesRecv;
    public String MobilePktRecv;
    public String incrementMobileRecv;
    public String incrementMobilePktRecv;
    public String Battery_level;
    public String contextMeasurementIntervel;
    public String contextTimestamp;
    public String MobileBytesRecv;
    public String incrementMobilePktSend;
    public String currentRssi;
    public String MobileBytesSend;

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
                .append("time_ms").append("\t")
                .append("filtered_mean_rtt_ms").append("\t")
                .append("real_hostname").append("\t")
                .append("address").append("\t")
                .append("error").append("\t")

                .append("hop_0_rtt_ms").append("\t")
                .append("hop_1_rtt_ms").append("\t")
                .append("hop_2_rtt_ms").append("\t")
                .append("hop_3_rtt_ms").append("\t")
                .append("hop_4_rtt_ms").append("\t")
                .append("hop_5_rtt_ms").append("\t")
                .append("hop_6_rtt_ms").append("\t")
                .append("hop_7_rtt_ms").append("\t")
                .append("hop_8_rtt_ms").append("\t")
                .append("hop_9_rtt_ms").append("\t")
                .append("hop_10_rtt_ms").append("\t")
                .append("hop_11_rtt_ms").append("\t")
                .append("hop_12_rtt_ms").append("\t")
                .append("hop_13_rtt_ms").append("\t")
                .append("hop_14_rtt_ms").append("\t")
                .append("hop_15_rtt_ms").append("\t")
                .append("hop_16_rtt_ms").append("\t")
                .append("hop_17_rtt_ms").append("\t")
                .append("hop_18_rtt_ms").append("\t")
                .append("hop_19_rtt_ms").append("\t")
                .append("hop_20_rtt_ms").append("\t")
                .append("hop_21_rtt_ms").append("\t")
                .append("hop_22_rtt_ms").append("\t")
                .append("hop_23_rtt_ms").append("\t")
                .append("hop_24_rtt_ms").append("\t")
                .append("hop_25_rtt_ms").append("\t")
                .append("hop_26_rtt_ms").append("\t")
                .append("hop_27_rtt_ms").append("\t")
                .append("hop_28_rtt_ms").append("\t")
                .append("hop_29_rtt_ms").append("\t")
                .append("hop_30_rtt_ms").append("\t")

                .append("hop_0_rtt_ms_1").append("\t")

                .append("code").append("\t")
                .append("body").append("\t")

                .append("hop_0_addr_1").append("\t")
                .append("hop_1_addr_1").append("\t")
                .append("hop_2_addr_1").append("\t")
                .append("hop_3_addr_1").append("\t")
                .append("hop_4_addr_1").append("\t")
                .append("hop_5_addr_1").append("\t")
                .append("hop_6_addr_1").append("\t")
                .append("hop_7_addr_1").append("\t")
                .append("hop_8_addr_1").append("\t")
                .append("hop_9_addr_1").append("\t")
                .append("hop_10_addr_1").append("\t")
                .append("hop_11_addr_1").append("\t")
                .append("hop_12_addr_1").append("\t")
                .append("hop_13_addr_1").append("\t")
                .append("hop_14_addr_1").append("\t")
                .append("hop_15_addr_1").append("\t")
                .append("hop_16_addr_1").append("\t")
                .append("hop_17_addr_1").append("\t")
                .append("hop_18_addr_1").append("\t")
                .append("hop_19_addr_1").append("\t")
                .append("hop_20_addr_1").append("\t")
                .append("hop_21_addr_1").append("\t")
                .append("hop_22_addr_1").append("\t")
                .append("hop_23_addr_1").append("\t")
                .append("hop_24_addr_1").append("\t")
                .append("hop_25_addr_1").append("\t")
                .append("hop_26_addr_1").append("\t")
                .append("hop_27_addr_1").append("\t")
                .append("hop_28_addr_1").append("\t")
                .append("hop_29_addr_1").append("\t")
                .append("hop_30_addr_1").append("\t")

                .append("hop_0_addr_2").append("\t")
                .append("hop_1_addr_2").append("\t")
                .append("hop_2_addr_2").append("\t")
                .append("hop_3_addr_2").append("\t")
                .append("hop_4_addr_2").append("\t")
                .append("hop_5_addr_2").append("\t")
                .append("hop_6_addr_2").append("\t")
                .append("hop_7_addr_2").append("\t")
                .append("hop_8_addr_2").append("\t")
                .append("hop_9_addr_2").append("\t")
                .append("hop_10_addr_2").append("\t")
                .append("hop_11_addr_2").append("\t")
                .append("hop_12_addr_2").append("\t")
                .append("hop_13_addr_2").append("\t")
                .append("hop_14_addr_2").append("\t")
                .append("hop_15_addr_2").append("\t")
                .append("hop_16_addr_2").append("\t")
                .append("hop_17_addr_2").append("\t")
                .append("hop_18_addr_2").append("\t")
                .append("hop_19_addr_2").append("\t")
                .append("hop_20_addr_2").append("\t")
                .append("hop_21_addr_2").append("\t")
                .append("hop_22_addr_2").append("\t")
                .append("hop_23_addr_2").append("\t")
                .append("hop_24_addr_2").append("\t")
                .append("hop_25_addr_2").append("\t")
                .append("hop_26_addr_2").append("\t")
                .append("hop_27_addr_2").append("\t")
                .append("hop_28_addr_2").append("\t")
                .append("hop_29_addr_2").append("\t")
                .append("hop_30_addr_2").append("\t")

                .append("hop_0_addr_3").append("\t")
                .append("hop_1_addr_3").append("\t")
                .append("hop_2_addr_3").append("\t")
                .append("hop_3_addr_3").append("\t")
                .append("hop_4_addr_3").append("\t")
                .append("hop_5_addr_3").append("\t")
                .append("hop_6_addr_3").append("\t")
                .append("hop_7_addr_3").append("\t")
                .append("hop_8_addr_3").append("\t")
                .append("hop_9_addr_3").append("\t")
                .append("hop_10_addr_3").append("\t")
                .append("hop_11_addr_3").append("\t")
                .append("hop_12_addr_3").append("\t")
                .append("hop_13_addr_3").append("\t")
                .append("hop_14_addr_3").append("\t")
                .append("hop_15_addr_3").append("\t")
                .append("hop_16_addr_3").append("\t")
                .append("hop_17_addr_3").append("\t")
                .append("hop_18_addr_3").append("\t")
                .append("hop_19_addr_3").append("\t")
                .append("hop_20_addr_3").append("\t")
                .append("hop_21_addr_3").append("\t")
                .append("hop_22_addr_3").append("\t")
                .append("hop_23_addr_3").append("\t")
                .append("hop_24_addr_3").append("\t")
                .append("hop_25_addr_3").append("\t")
                .append("hop_26_addr_3").append("\t")
                .append("hop_27_addr_3").append("\t")
                .append("hop_28_addr_3").append("\t")
                .append("hop_29_addr_3").append("\t")
                .append("hop_30_addr_3").append("\t")

                .append("body_len").append("\t")
                .append("headers").append("\t")
                .append("headers_len").append("\t")
                .append("num_hops").append("\t")
                .append("PRR").append("\t")
                .append("throught").append("\t")
                .append("speed").append("\t")
                .append("speedAll").append("\t")
                .append("speedDisplay").append("\t")
                .append("duration").append("\t")
                .append("data_limit_exceeded").append("\t")
                .append("server_version").append("\t")
                .append("tcp_speed_results").append("\t")
                .append("ping_method").append("\t")
                .append("currentBatteryLevel").append("\t")
                .append("incrementMobileSend").append("\t")
                .append("incrementMobileBytesRecv").append("\t")
                .append("MobilePktRecv").append("\t")
                .append("incrementMobileRecv").append("\t")
                .append("incrementMobilePktRecv").append("\t")
                .append("Battery_level").append("\t")
                .append("contextMeasurementIntervel").append("\t")
                .append("contextTimestamp").append("\t")
                .append("MobileBytesRecv").append("\t")
                .append("incrementMobilePktSend").append("\t")
                .append("currentRssi").append("\t")
                .append("MobileBytesSend");
        return(sb.toString());
    }

    public String toTSV() {
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
                .append(time_ms).append("\t")
                .append(filtered_mean_rtt_ms).append("\t")
                .append(real_hostname).append("\t")
                .append(address).append("\t")
                .append(error).append("\t")

                .append(hop_0_rtt_ms).append("\t")
                .append(hop_1_rtt_ms).append("\t")
                .append(hop_2_rtt_ms).append("\t")
                .append(hop_3_rtt_ms).append("\t")
                .append(hop_4_rtt_ms).append("\t")
                .append(hop_5_rtt_ms).append("\t")
                .append(hop_6_rtt_ms).append("\t")
                .append(hop_7_rtt_ms).append("\t")
                .append(hop_8_rtt_ms).append("\t")
                .append(hop_9_rtt_ms).append("\t")
                .append(hop_10_rtt_ms).append("\t")
                .append(hop_11_rtt_ms).append("\t")
                .append(hop_12_rtt_ms).append("\t")
                .append(hop_13_rtt_ms).append("\t")
                .append(hop_14_rtt_ms).append("\t")
                .append(hop_15_rtt_ms).append("\t")
                .append(hop_16_rtt_ms).append("\t")
                .append(hop_17_rtt_ms).append("\t")
                .append(hop_18_rtt_ms).append("\t")
                .append(hop_19_rtt_ms).append("\t")
                .append(hop_20_rtt_ms).append("\t")
                .append(hop_21_rtt_ms).append("\t")
                .append(hop_22_rtt_ms).append("\t")
                .append(hop_23_rtt_ms).append("\t")
                .append(hop_24_rtt_ms).append("\t")
                .append(hop_25_rtt_ms).append("\t")
                .append(hop_26_rtt_ms).append("\t")
                .append(hop_27_rtt_ms).append("\t")
                .append(hop_28_rtt_ms).append("\t")
                .append(hop_29_rtt_ms).append("\t")
                .append(hop_30_rtt_ms).append("\t")

                .append(hop_0_rtt_ms_1).append("\t")

                .append(code).append("\t")
                .append(body).append("\t")

                .append(hop_0_addr_1).append("\t")
                .append(hop_1_addr_1).append("\t")
                .append(hop_2_addr_1).append("\t")
                .append(hop_3_addr_1).append("\t")
                .append(hop_4_addr_1).append("\t")
                .append(hop_5_addr_1).append("\t")
                .append(hop_6_addr_1).append("\t")
                .append(hop_7_addr_1).append("\t")
                .append(hop_8_addr_1).append("\t")
                .append(hop_9_addr_1).append("\t")
                .append(hop_10_addr_1).append("\t")
                .append(hop_11_addr_1).append("\t")
                .append(hop_12_addr_1).append("\t")
                .append(hop_13_addr_1).append("\t")
                .append(hop_14_addr_1).append("\t")
                .append(hop_15_addr_1).append("\t")
                .append(hop_16_addr_1).append("\t")
                .append(hop_17_addr_1).append("\t")
                .append(hop_18_addr_1).append("\t")
                .append(hop_19_addr_1).append("\t")
                .append(hop_10_addr_1).append("\t")
                .append(hop_11_addr_1).append("\t")
                .append(hop_12_addr_1).append("\t")
                .append(hop_13_addr_1).append("\t")
                .append(hop_14_addr_1).append("\t")
                .append(hop_15_addr_1).append("\t")
                .append(hop_16_addr_1).append("\t")
                .append(hop_17_addr_1).append("\t")
                .append(hop_18_addr_1).append("\t")
                .append(hop_19_addr_1).append("\t")
                .append(hop_20_addr_1).append("\t")
                .append(hop_21_addr_1).append("\t")
                .append(hop_22_addr_1).append("\t")
                .append(hop_23_addr_1).append("\t")
                .append(hop_24_addr_1).append("\t")
                .append(hop_25_addr_1).append("\t")
                .append(hop_26_addr_1).append("\t")
                .append(hop_27_addr_1).append("\t")
                .append(hop_28_addr_1).append("\t")
                .append(hop_29_addr_1).append("\t")
                .append(hop_30_addr_1).append("\t")

                .append(hop_0_addr_2).append("\t")
                .append(hop_1_addr_2).append("\t")
                .append(hop_2_addr_2).append("\t")
                .append(hop_3_addr_2).append("\t")
                .append(hop_4_addr_2).append("\t")
                .append(hop_5_addr_2).append("\t")
                .append(hop_6_addr_2).append("\t")
                .append(hop_7_addr_2).append("\t")
                .append(hop_8_addr_2).append("\t")
                .append(hop_9_addr_2).append("\t")
                .append(hop_10_addr_2).append("\t")
                .append(hop_11_addr_2).append("\t")
                .append(hop_12_addr_2).append("\t")
                .append(hop_13_addr_2).append("\t")
                .append(hop_14_addr_2).append("\t")
                .append(hop_15_addr_2).append("\t")
                .append(hop_16_addr_2).append("\t")
                .append(hop_17_addr_2).append("\t")
                .append(hop_18_addr_2).append("\t")
                .append(hop_19_addr_2).append("\t")
                .append(hop_20_addr_2).append("\t")
                .append(hop_21_addr_2).append("\t")
                .append(hop_22_addr_2).append("\t")
                .append(hop_23_addr_2).append("\t")
                .append(hop_24_addr_2).append("\t")
                .append(hop_25_addr_2).append("\t")
                .append(hop_26_addr_2).append("\t")
                .append(hop_27_addr_2).append("\t")
                .append(hop_28_addr_2).append("\t")
                .append(hop_29_addr_2).append("\t")
                .append(hop_30_addr_2).append("\t")

                .append(hop_0_addr_3).append("\t")
                .append(hop_1_addr_3).append("\t")
                .append(hop_2_addr_3).append("\t")
                .append(hop_3_addr_3).append("\t")
                .append(hop_4_addr_3).append("\t")
                .append(hop_5_addr_3).append("\t")
                .append(hop_6_addr_3).append("\t")
                .append(hop_7_addr_3).append("\t")
                .append(hop_8_addr_3).append("\t")
                .append(hop_9_addr_3).append("\t")
                .append(hop_10_addr_3).append("\t")
                .append(hop_11_addr_3).append("\t")
                .append(hop_12_addr_3).append("\t")
                .append(hop_13_addr_3).append("\t")
                .append(hop_14_addr_3).append("\t")
                .append(hop_15_addr_3).append("\t")
                .append(hop_16_addr_3).append("\t")
                .append(hop_17_addr_3).append("\t")
                .append(hop_18_addr_3).append("\t")
                .append(hop_19_addr_3).append("\t")
                .append(hop_20_addr_3).append("\t")
                .append(hop_21_addr_3).append("\t")
                .append(hop_22_addr_3).append("\t")
                .append(hop_23_addr_3).append("\t")
                .append(hop_24_addr_3).append("\t")
                .append(hop_25_addr_3).append("\t")
                .append(hop_26_addr_3).append("\t")
                .append(hop_27_addr_3).append("\t")
                .append(hop_28_addr_3).append("\t")
                .append(hop_29_addr_3).append("\t")
                .append(hop_30_addr_3).append("\t")

                .append(body_len).append("\t")
                .append(headers_len).append("\t")
                .append(headers).append("\t")
                .append(num_hops).append("\t")
                .append(PRR).append("\t")
                .append(throught).append("\t")
                .append(speed).append("\t")
                .append(speedAll).append("\t")
                .append(speedDisplay).append("\t")
                .append(duration).append("\t")
                .append(data_limit_exceeded).append("\t")
                .append(server_version).append("\t")
                .append(tcp_speed_results).append("\t")
                .append(ping_method).append("\t")
                .append(currentBatteryLevel).append("\t")
                .append(incrementMobileSend).append("\t")
                .append(incrementMobileBytesRecv).append("\t")
                .append(MobilePktRecv).append("\t")
                .append(incrementMobileRecv).append("\t")
                .append(incrementMobilePktRecv).append("\t")
                .append(Battery_level).append("\t")
                .append(contextMeasurementIntervel).append("\t")
                .append(contextTimestamp).append("\t")
                .append(MobileBytesRecv).append("\t")
                .append(incrementMobilePktSend).append("\t")
                .append(currentRssi).append("\t")
                .append(MobileBytesSend);
        return(sb.toString());
    }
}

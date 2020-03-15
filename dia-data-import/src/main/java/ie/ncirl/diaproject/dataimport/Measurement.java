package ie.ncirl.diaproject.dataimport;

public class Measurement {
    public Task task;
    public Parameters parameters;
    public String success;
    public String timestamp;
    public DeviceProperties device_properties;
    public Values values;
    public String type;
    public String id;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append(Task.toHdr()).append("\t")
                .append(TaskParameters.toHdr()).append("\t")
                .append("success").append("\t")
                .append("timestamp").append("\t")
                .append(DeviceProperties.toHdr()).append("\t")
                .append(Values.toHdr()).append("\t")
                .append("type").append("\t")
                .append("id");
        return(sb.toString());
    }

    public String toTSV() {
        StringBuffer sb = new StringBuffer()
                .append(task.toTSV()).append("\t")
                .append(parameters.toTSV()).append("\t")
                .append(success).append("\t")
                .append(timestamp).append("\t")
                .append(device_properties.toTSV()).append("\t")
                .append(values.toTSV()).append("\t")
                .append(type).append("\t")
                .append(id);
        return(sb.toString());
    }

    public static class Task {
        public String count;
        public String filter;
        public TaskParameters parameters;
        public String created;
        public String start_time;
        public String interval_sec;
        public String priority;
        public String tag;
        public String end_time;
        public String type;
        public String id;

        public static String toHdr() {
            StringBuffer sb = new StringBuffer()
                    .append("count").append("\t")
                    .append("filter").append("\t")
                    .append(Parameters.toHdr()).append("\t")
                    .append("created").append("\t")
                    .append("start_time").append("\t")
                    .append("interval_sec").append("\t")
                    .append("priority").append("\t")
                    .append("tag").append("\t")
                    .append("end_time").append("\t")
                    .append("type").append("\t")
                    .append("id");
            return(sb.toString());
        }

        public String toTSV() {
            StringBuffer sb = new StringBuffer()
                    .append(count).append("\t")
                    .append(filter).append("\t")
                    .append(parameters.toTSV()).append("\t")
                    .append(created).append("\t")
                    .append(start_time).append("\t")
                    .append(interval_sec).append("\t")
                    .append(priority).append("\t")
                    .append(tag).append("\t")
                    .append(end_time).append("\t")
                    .append(type).append("\t")
                    .append(id);
            return(sb.toString());
        }
    }

    public static class TaskParameters {
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
                    .append("method");
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
                    .append(method);
            return(sb.toString());
        }
    }

    public static class Parameters {
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
                    .append("method");
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
                    .append(method);
            return(sb.toString());
        }
    }

    public static class DeviceProperties {
        public String battery_level;
        public String cell_info;
        public String timestamp;
        public String network_type;
        public String os_version;
        public DeviceInfo device_info;
        public String carrier;
        public Location location;
        public String rssi;
        public String app_version;
        public String location_type;
        public String is_battery_charging;

        public static String toHdr() {
            StringBuffer sb = new StringBuffer()
                    .append("battery_level").append("\t")
                    .append("cell_info").append("\t")
                    .append("timestamp").append("\t")
                    .append("network_type").append("\t")
                    .append("os_version").append("\t")
                    .append(DeviceInfo.toHdr()).append("\t")
                    .append("carrier").append("\t")
                    .append(Location.toHdr()).append("\t")
                    .append("rssi").append("\t")
                    .append("app_version").append("\t")
                    .append("location_type").append("\t")
                    .append("is_battery_charging");
            return(sb.toString());
        }

        public String toTSV() {
            StringBuffer sb = new StringBuffer()
                    .append(battery_level).append("\t")
                    .append(cell_info).append("\t")
                    .append(timestamp).append("\t")
                    .append(network_type).append("\t")
                    .append(os_version).append("\t")
                    .append(device_info.toTSV()).append("\t")
                    .append(carrier).append("\t")
                    .append(location.toTSV()).append("\t")
                    .append(rssi).append("\t")
                    .append(app_version).append("\t")
                    .append(location_type).append("\t")
                    .append(is_battery_charging);
            return(sb.toString());
        }
    }

    public static class DeviceInfo {
        public String model;
        public String os;
        public String manufacturer;

        public static String toHdr() {
            StringBuffer sb = new StringBuffer()
                    .append("model").append("\t")
                    .append("os").append("\t")
                    .append("manufacturer");
            return(sb.toString());
        }

        public String toTSV() {
            StringBuffer sb = new StringBuffer()
                    .append(model).append("\t")
                    .append(os).append("\t")
                    .append(manufacturer);
            return(sb.toString());
        }
    }

    public static class Location {
        public String latitude;
        public String longitude;

        public static String toHdr() {
            StringBuffer sb = new StringBuffer()
                    .append("latitude").append("\t")
                    .append("longitude");
            return(sb.toString());
        }

        public String toTSV() {
            StringBuffer sb = new StringBuffer()
                    .append(latitude).append("\t")
                    .append(longitude);
            return(sb.toString());
        }
    }

    public static class Values {
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
        public String hop_7_rtt_ms;
        public String hop_11_rtt_ms;
        public String hop_12_rtt_ms;
        public String hop_13_rtt_ms;
        public String hop_14_rtt_ms;
        public String code;
        public String body;
        public String hop_0_addr_1;
        public String hop_4_addr_1;
        public String hop_7_addr_1;
        public String hop_10_addr_1;
        public String hop_11_addr_1;
        public String hop_15_addr_1;
        public String hop_1_addr_2;
        public String hop_11_addr_2;
        public String hop_15_addr_2;
        public String hop_16_addr_2;
        public String body_len;
        public String headers;
        public String headers_len;
        public String num_hops;

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
                    .append("hop_7_rtt_ms").append("\t")
                    .append("hop_11_rtt_ms").append("\t")
                    .append("hop_12_rtt_ms").append("\t")
                    .append("hop_13_rtt_ms").append("\t")
                    .append("hop_14_rtt_ms").append("\t")
                    .append("code").append("\t")
                    .append("body").append("\t")
                    .append("hop_0_addr_1").append("\t")
                    .append("hop_4_addr_1").append("\t")
                    .append("hop_7_addr_1").append("\t")
                    .append("hop_10_addr_1").append("\t")
                    .append("hop_11_addr_1").append("\t")
                    .append("hop_15_addr_1").append("\t")
                    .append("hop_1_addr_2").append("\t")
                    .append("hop_11_addr_2").append("\t")
                    .append("hop_15_addr_2").append("\t")
                    .append("hop_16_addr_2").append("\t")
                    .append("body_len").append("\t")
                    .append("headers").append("\t")
                    .append("headers_len").append("\t")
                    .append("num_hops");
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
                    .append(hop_7_rtt_ms).append("\t")
                    .append(hop_11_rtt_ms).append("\t")
                    .append(hop_12_rtt_ms).append("\t")
                    .append(hop_13_rtt_ms).append("\t")
                    .append(hop_14_rtt_ms).append("\t")
                    .append(code).append("\t")
                    .append(body).append("\t")
                    .append(hop_0_addr_1).append("\t")
                    .append(hop_4_addr_1).append("\t")
                    .append(hop_7_addr_1).append("\t")
                    .append(hop_10_addr_1).append("\t")
                    .append(hop_11_addr_1).append("\t")
                    .append(hop_15_addr_1).append("\t")
                    .append(hop_1_addr_2).append("\t")
                    .append(hop_11_addr_2).append("\t")
                    .append(hop_15_addr_2).append("\t")
                    .append(hop_16_addr_2).append("\t")
                    .append(body_len).append("\t")
                    .append(headers_len).append("\t")
                    .append(headers).append("\t")
                    .append(num_hops);
            return(sb.toString());
        }
    }
}

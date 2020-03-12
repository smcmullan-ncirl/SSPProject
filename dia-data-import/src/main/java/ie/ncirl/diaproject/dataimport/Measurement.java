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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
    }

    public static class TaskParameters {
        public String location_update_distance;
        public String target;
        public String trigger_location_update;
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
    }

    public static class DeviceInfo {
        public String model;
        public String os;
        public String manufacturer;
    }

    public static class Location {
        public String latitude;
        public String longitude;
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
    }
}

package ie.ncirl.diaproject.dataimport;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Measurement {
    @JsonUnwrapped public Task task;
    @JsonUnwrapped public Parameters parameters;
    //public String success;
    //public String timestamp;
    //@JsonUnwrapped @JsonAlias({"device_properties"}) public DeviceProperties deviceProperties;
    //@JsonUnwrapped public Values values;
    //public String type;
    //public String id;

    public static class Task {
        public String count;
        public String filter;
        @JsonUnwrapped @JsonAlias({"parameters"}) public TaskParameters taskParams;
        public String created;
        @JsonAlias({"start_time"}) public String startTime;
        @JsonAlias({"interval_sec"}) public String intervalSec;
        public String priority;
        public String tag;
        @JsonAlias({"end_time"}) public String endTime;
        public String type;
        public String id;
    }

    public static class TaskParameters {
        @JsonAlias({"location_update_distance"}) public String locationUpdateDistance;
        public String target;
        @JsonAlias({"trigger_location_update"}) public String triggerLocationUpdate;
    }

    public static class Parameters {
        public String count;
        public String target;
        @JsonAlias({"parameters"}) public String params;
        //@JsonAlias({"start_time"}) public String startTime;
        //@JsonAlias({"interval_sec"}) public String intervalSec;
        //@JsonAlias({"ping_timeout_sec"}) public String pingTimeoutSec;
        //public String priority;
        //@JsonAlias({"packet_size_byte"}) public String packetSizeByte;
        //@JsonAlias({"ping_exe"}) public String pingExe;
        //public String key;
        //public String type;
        //@JsonAlias({"end_time"}) public String endTime;
    }

    public static class DeviceProperties {
        @JsonAlias({"battery_level"}) public String batteryLevel;
        @JsonAlias({"cell_info"}) public String cellInfo;
        public String timestamp;
        @JsonAlias({"network_type"}) public String networkType;
        @JsonAlias({"os_version"}) public String osVersion;
        @JsonUnwrapped @JsonAlias({"device_info"})  public DeviceInfo deviceInfo;
        public String carrier;
        @JsonUnwrapped public Location location;
        public String rssi;
        @JsonAlias({"app_version"}) public String appVersion;
        @JsonAlias({"location_type"}) public String locationType;
        @JsonAlias({"is_battery_charging"}) public String isBatteryCharging;
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
        @JsonAlias({"packets_sent"}) public String packetsSent;
        @JsonAlias({"packet_loss"}) public String packetLoss;
        @JsonAlias({"mean_rtt_ms"}) public String meanRttMs;
        @JsonAlias({"CalledByLocation"}) public String calledByLocation;
        @JsonAlias({"MeasurementLatitude"}) public String measurementLatitude;
        @JsonAlias({"max_rtt_ms"}) public String maxRttMs;
        @JsonAlias({"target_ip"}) public String targetIp;
        @JsonAlias({"stddev_rtt_ms"}) public String stddevRttMs;
        @JsonAlias({"min_rtt_ms"}) public String minRttMs;
        @JsonAlias({"MeasurementLongitude"}) public String measurementLongitude;
    }
}

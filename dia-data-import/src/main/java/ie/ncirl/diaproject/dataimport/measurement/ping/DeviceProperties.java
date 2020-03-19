package ie.ncirl.diaproject.dataimport.measurement.ping;

import java.util.Arrays;

public class DeviceProperties {

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
    public String[] host_apps;
    public String registration_id;
    public String mobilyzer_version;
    public String request_app;
    public String ssid;
    public String bssid;
    public String cell_rssi;
    public String wifi_ip_address;
    public String country_code;

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
                .append("is_battery_charging").append("\t")
                .append("host_apps").append("\t")
                .append("registration_id").append("\t")
                .append("mobilyzer_version").append("\t")
                .append("request_app").append("\t")
                .append("ssid").append("\t")
                .append("bssid").append("\t")
                .append("cell_rssi").append("\t")
                .append("wifi_ip_address").append("\t")
                .append("country_code");
        return(sb.toString());
    }

    public String toTsv() throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(battery_level).append("\t")
                .append(cell_info).append("\t")
                .append(timestamp).append("\t")
                .append(network_type).append("\t")
                .append(os_version).append("\t")
                .append(device_info.toTsv()).append("\t")
                .append(carrier).append("\t")
                .append(location.toTsv()).append("\t")
                .append(rssi).append("\t")
                .append(app_version).append("\t")
                .append(location_type).append("\t")
                .append(is_battery_charging).append("\t")
                .append(Arrays.toString(host_apps)).append("\t")
                .append(registration_id).append("\t")
                .append(mobilyzer_version).append("\t")
                .append(request_app).append("\t")
                .append(ssid).append("\t")
                .append(bssid).append("\t")
                .append(cell_rssi).append("\t")
                .append(wifi_ip_address).append("\t")
                .append(country_code);
        return(sb.toString());
    }

}

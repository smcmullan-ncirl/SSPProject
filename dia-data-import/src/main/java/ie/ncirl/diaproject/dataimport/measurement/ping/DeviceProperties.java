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

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("dp_battery_level").append(sep)
                .append("dp_cell_info").append(sep)
                .append("dp_timestamp").append(sep)
                .append("dp_network_type").append(sep)
                .append("dp_os_version").append(sep)
                .append(DeviceInfo.toHdr(sep)).append(sep)
                .append("dp_carrier").append(sep)
                .append(Location.toHdr(sep)).append(sep)
                .append("dp_rssi").append(sep)
                .append("dp_app_version").append(sep)
                .append("dp_location_type").append(sep)
                .append("dp_is_battery_charging").append(sep)
                .append("dp_host_apps").append(sep)
                .append("dp_registration_id").append(sep)
                .append("dp_mobilyzer_version").append(sep)
                .append("dp_request_app").append(sep)
                .append("dp_ssid").append(sep)
                .append("dp_bssid").append(sep)
                .append("dp_cell_rssi").append(sep)
                .append("dp_wifi_ip_address").append(sep)
                .append("dp_country_code");
        return(sb.toString());
    }

    public String toCsv(String sep, String quote) throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(battery_level).append(sep)
                .append(cell_info).append(sep)
                .append(timestamp).append(sep)
                .append(network_type).append(sep)
                .append(os_version).append(sep)
                .append(device_info.toCsv(sep, quote)).append(sep)
                .append(carrier).append(sep)
                .append(location.toCsv(sep, quote)).append(sep)
                .append(rssi).append(sep)
                .append(app_version).append(sep)
                .append(location_type).append(sep)
                .append(is_battery_charging).append(sep)
                .append(Arrays.toString(host_apps)).append(sep)
                .append(registration_id).append(sep)
                .append(mobilyzer_version).append(sep)
                .append(request_app).append(sep)
                .append(ssid).append(sep)
                .append(bssid).append(sep)
                .append(cell_rssi).append(sep)
                .append(wifi_ip_address).append(sep)
                .append(country_code);
        return(sb.toString());
    }

}

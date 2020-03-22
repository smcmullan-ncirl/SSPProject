package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

import java.util.Arrays;

public class DeviceProperties extends Measurement {

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

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "dp_battery_level", sep);
        separate(sb, "dp_cell_info", sep);
        separate(sb, "dp_timestamp", sep);
        separate(sb, "dp_network_type", sep);
        separate(sb, "dp_os_version", sep);
        separate(sb, (new DeviceInfo()).toHdr(sep), sep);
        separate(sb, "dp_carrier", sep);
        separate(sb, (new Location()).toHdr(sep), sep);
        separate(sb, "dp_rssi", sep);
        separate(sb, "dp_app_version", sep);
        separate(sb, "dp_location_type", sep);
        separate(sb, "dp_is_battery_charging", sep);
        separate(sb, "dp_host_apps", sep);
        separate(sb, "dp_registration_id", sep);
        separate(sb, "dp_mobilyzer_version", sep);
        separate(sb, "dp_request_app", sep);
        separate(sb, "dp_ssid", sep);
        separate(sb, "dp_bssid", sep);
        separate(sb, "dp_cell_rssi", sep);
        separate(sb, "dp_wifi_ip_address", sep);
        separate(sb, "dp_country_code", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String quote, String sep) throws NullPointerException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, battery_level, quote, sep);
        quoteAndSeparate(sb, cell_info, quote, sep);
        quoteAndSeparate(sb, timestamp, quote, sep);
        quoteAndSeparate(sb, network_type, quote, sep);
        quoteAndSeparate(sb, os_version, quote, sep);

        separate(sb, device_info != null
                ? device_info.toCsv(quote, sep)
                : (new DeviceInfo()).toNullCsv(sep), sep);

        quoteAndSeparate(sb, carrier, quote, sep);

        separate(sb, location != null
                ? location.toCsv(quote, sep)
                : (new Location()).toNullCsv(sep), sep);

        quoteAndSeparate(sb, rssi, quote, sep);
        quoteAndSeparate(sb, app_version, quote, sep);
        quoteAndSeparate(sb, location_type, quote, sep);
        quoteAndSeparate(sb, is_battery_charging, quote, sep);
        quoteAndSeparate(sb, Arrays.toString(host_apps), quote, sep);
        quoteAndSeparate(sb, registration_id, quote, sep);
        quoteAndSeparate(sb, mobilyzer_version, quote, sep);
        quoteAndSeparate(sb, request_app, quote, sep);
        quoteAndSeparate(sb, ssid, quote, sep);
        quoteAndSeparate(sb, bssid, quote, sep);
        quoteAndSeparate(sb, cell_rssi, quote, sep);
        quoteAndSeparate(sb, wifi_ip_address, quote, sep);
        quoteAndSeparate(sb, country_code, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep) // battery_level
                .append(sep) // cell_info
                .append(sep) // timestamp
                .append(sep) // network_type
                .append(sep) // os_version
                .append((new DeviceInfo()).toNullCsv(sep)).append(sep) // device_info
                .append(sep) // carrier
                .append((new Location()).toNullCsv(sep)).append(sep) // location
                .append(sep) // rssi
                .append(sep) // app_version
                .append(sep) // location_type
                .append(sep) // is_battery_charging
                .append(sep) // host_apps
                .append(sep) // registration_id
                .append(sep) // mobilyzer_version
                .append(sep) // request_app
                .append(sep) // ssid
                .append(sep) // bssid
                .append(sep) // cell_rssi
                .append(sep) // wifi_ip_address
                .append(NO_SEP); // country_code
        return(sb.toString());
    }
}

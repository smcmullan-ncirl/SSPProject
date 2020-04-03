package ie.ncirl.diaproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

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
    public String country_code;
    public String wifi_ip_address;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        return null;
    }
}

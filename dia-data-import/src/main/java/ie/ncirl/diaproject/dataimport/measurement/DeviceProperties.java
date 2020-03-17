package ie.ncirl.diaproject.dataimport.measurement;

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

    public String toTSV() throws NullPointerException {
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

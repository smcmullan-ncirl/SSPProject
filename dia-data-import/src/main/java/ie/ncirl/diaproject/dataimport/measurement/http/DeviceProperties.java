package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceProperties extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(DeviceProperties.class);

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
        separate(sb, "dp_is_battery_charging", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws NullPointerException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, battery_level, quote, sep);
        quoteAndSeparate(sb, cell_info, quote, sep);
        quoteAndSeparate(sb, timestamp, quote, sep);
        quoteAndSeparate(sb, network_type, quote, sep);
        quoteAndSeparate(sb, os_version, quote, sep);

        separate(sb, device_info != null
                ? device_info.toCsv(nullValue, quote, sep)
                : (new DeviceInfo()).toNullCsv(nullValue, sep), sep);

        quoteAndSeparate(sb, carrier, quote, sep);

        separate(sb, location != null
                ? location.toCsv(nullValue, quote, sep)
                : (new Location()).toNullCsv(nullValue, sep), sep);

        quoteAndSeparate(sb, rssi, quote, sep);
        quoteAndSeparate(sb, app_version, quote, sep);
        quoteAndSeparate(sb, location_type, quote, sep);
        quoteAndSeparate(sb, is_battery_charging, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // battery_level
        separate(sb, nullValue, sep); // cell_info
        separate(sb, nullValue, sep); // timestamp
        separate(sb, nullValue, sep); // network_type
        separate(sb, nullValue, sep); // os_version
        separate(sb, (new DeviceInfo()).toNullCsv(nullValue, sep), sep); // device_info
        separate(sb, nullValue, sep); // carrier
        separate(sb, (new Location()).toNullCsv(nullValue, sep), sep); // location
        separate(sb, nullValue, sep); // rssi
        separate(sb, nullValue, sep); // app_version
        separate(sb, nullValue, sep); // location_type
        separate(sb, nullValue, NO_SEP); // is_battery_charging
        return(sb.toString());
    }
}

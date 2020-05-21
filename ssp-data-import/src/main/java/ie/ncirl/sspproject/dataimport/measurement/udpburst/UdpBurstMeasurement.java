package ie.ncirl.sspproject.dataimport.measurement.udpburst;

import ie.ncirl.sspproject.dataimport.measurement.DeviceProperties;
import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class UdpBurstMeasurement extends Measurement {

    public Task task;
    public Parameters parameters;
    public String success;
    public String timestamp;
    public DeviceProperties device_properties;
    public Values values;
    public String type;
    public String id;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, (new Task()).toHdr(sep), sep);
        separate(sb, (new Parameters()).toHdr(sep), sep);
        separate(sb, "m_success", sep);
        separate(sb, "m_timestamp", sep);
        separate(sb, (new DeviceProperties()).toHdr(sep), sep);
        separate(sb, (new Values()).toHdr(sep), sep);
        separate(sb, "m_type", sep);
        separate(sb, "m_id", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();

        separate(sb, task != null
                ? task.toCsv(nullValue, quote, sep)
                : (new Task()).toNullCsv(nullValue, sep), sep);

        separate(sb, parameters != null
                ? parameters.toCsv(nullValue, quote, sep)
                : (new Parameters()).toNullCsv(nullValue, sep), sep);

        quoteAndSeparate(sb, success, quote, sep);
        quoteAndSeparate(sb, timestamp, quote, sep);

        separate(sb, device_properties != null
                ? device_properties.toCsv(nullValue, quote, sep)
                : (new DeviceProperties()).toNullCsv(nullValue, sep), sep);

        separate(sb, values != null
                ? values.toCsv(nullValue, quote, sep)
                : (new Values()).toNullCsv(nullValue, sep), sep);

        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, id, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, (new Task()).toNullCsv(nullValue, sep), sep); // task
        separate(sb, (new Parameters()).toNullCsv(nullValue, sep), sep); // parameters
        separate(sb, nullValue, sep); // success
        separate(sb, nullValue, sep); // timestamp
        separate(sb, (new DeviceProperties()).toNullCsv(nullValue, sep), sep); // device_properties
        separate(sb, (new Values()).toNullCsv(nullValue, sep), sep); // values
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, NO_SEP); // id
        return(sb.toString());
    }
}

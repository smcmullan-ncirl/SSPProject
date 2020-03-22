package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class PingMeasurement extends Measurement {

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
    public String toCsv(String quote, String sep) throws NullPointerException {
        StringBuffer sb = new StringBuffer();

        separate(sb, task != null
                ? task.toCsv(quote, sep)
                : (new Task()).toNullCsv(sep), sep);

        separate(sb, parameters != null
                ? parameters.toCsv(quote, sep)
                : (new Parameters()).toNullCsv(sep), sep);

        quoteAndSeparate(sb, success, quote, sep);
        quoteAndSeparate(sb, timestamp, quote, sep);

        separate(sb, device_properties != null
                ? device_properties.toCsv(quote, sep)
                : (new DeviceProperties()).toNullCsv(sep), sep);

        separate(sb, values != null
                ? values.toCsv(quote, sep)
                : (new Values()).toNullCsv(sep), sep);

        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, id, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append((new Task()).toNullCsv(sep)).append(sep) // task
                .append((new TaskParameters()).toNullCsv(sep)).append(sep) // parameters
                .append(sep) // success
                .append(sep) // timestamp
                .append((new DeviceProperties()).toNullCsv(sep)).append(sep) // device_properties
                .append(sep) // values
                .append(sep) // type
                .append(NO_SEP); // id
        return(sb.toString());
    }
}

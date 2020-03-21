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
        StringBuffer sb = new StringBuffer()
                .append(Task.toHdr(sep)).append(sep)
                .append(Parameters.toHdr(sep)).append(sep)
                .append("m_success").append(sep)
                .append("m_timestamp").append(sep)
                .append(DeviceProperties.toHdr(sep)).append(sep)
                .append(Values.toHdr(sep)).append(sep)
                .append("m_type").append(sep)
                .append("m_id");
        return(sb.toString());
    }

    @Override
    public String toCsv(String sep, String quote) throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(task != null ? task.toCsv(sep, quote) : Task.toNullCsv(sep)).append(sep)
                .append(parameters.toCsv(sep, quote)).append(sep)
                .append(success).append(sep)
                .append(timestamp).append(sep)
                .append(device_properties.toCsv(sep, quote)).append(sep)
                .append(values.toCsv(sep, quote)).append(sep)
                .append(type).append(sep)
                .append(id);
        return(sb.toString());
    }

}

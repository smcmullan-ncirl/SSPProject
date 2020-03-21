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
    public String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append(Task.toHdr()).append("\t")
                .append(Parameters.toHdr()).append("\t")
                .append("m_success").append("\t")
                .append("m_timestamp").append("\t")
                .append(DeviceProperties.toHdr()).append("\t")
                .append(Values.toHdr()).append("\t")
                .append("m_type").append("\t")
                .append("m_id");
        return(sb.toString());
    }

    @Override
    public String toTsv() throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(task != null ? task.toTsv() : Task.toNullTsv()).append("\t")
                .append(parameters.toTsv()).append("\t")
                .append(success).append("\t")
                .append(timestamp).append("\t")
                .append(device_properties.toTsv()).append("\t")
                .append(values.toTsv()).append("\t")
                .append(type).append("\t")
                .append(id);
        return(sb.toString());
    }

}

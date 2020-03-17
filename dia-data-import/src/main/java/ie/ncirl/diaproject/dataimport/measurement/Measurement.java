package ie.ncirl.diaproject.dataimport.measurement;

public class Measurement {
    public Task task;
    public Parameters parameters;
    public String success;
    public String timestamp;
    public DeviceProperties device_properties;
    public Values values;
    public String type;
    public String id;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append(Task.toHdr()).append("\t")
                .append(Parameters.toHdr()).append("\t")
                .append("success").append("\t")
                .append("timestamp").append("\t")
                .append(DeviceProperties.toHdr()).append("\t")
                .append(Values.toHdr()).append("\t")
                .append("type").append("\t")
                .append("id");
        return(sb.toString());
    }

    public String toTSV() throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(task != null ? task.toTSV() : Task.toNullTsv()).append("\t")
                .append(parameters.toTSV()).append("\t")
                .append(success).append("\t")
                .append(timestamp).append("\t")
                .append(device_properties.toTSV()).append("\t")
                .append(values.toTSV()).append("\t")
                .append(type).append("\t")
                .append(id);
        return(sb.toString());
    }
}

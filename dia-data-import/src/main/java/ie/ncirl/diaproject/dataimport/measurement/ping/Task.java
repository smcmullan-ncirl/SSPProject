package ie.ncirl.diaproject.dataimport.measurement.ping;

public class Task {

    public String count;
    public String filter;
    public TaskParameters parameters;
    public String created;
    public String start_time;
    public String interval_sec;
    public String priority;
    public String tag;
    public String end_time;
    public String type;
    public String id;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("t_count").append("\t")
                .append("t_filter").append("\t")
                .append(TaskParameters.toHdr()).append("\t")
                .append("t_created").append("\t")
                .append("t_start_time").append("\t")
                .append("t_interval_sec").append("\t")
                .append("t_priority").append("\t")
                .append("t_tag").append("\t")
                .append("t_end_time").append("\t")
                .append("t_type").append("\t")
                .append("t_id");
        return(sb.toString());
    }

    public static String toNullTsv() {
        StringBuffer sb = new StringBuffer()
                .append("\t").append("\t")
                .append(TaskParameters.toNullTsv()).append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t")
                .append("\t");
        return(sb.toString());
    }

    public String toTsv() throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(count).append("\t")
                .append(filter).append("\t")
                .append(parameters.toTsv()).append("\t")
                .append(created).append("\t")
                .append(start_time).append("\t")
                .append(interval_sec).append("\t")
                .append(priority).append("\t")
                .append(tag).append("\t")
                .append(end_time).append("\t")
                .append(type).append("\t")
                .append(id);
        return(sb.toString());
    }

}

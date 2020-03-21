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

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("t_count").append(sep)
                .append("t_filter").append(sep)
                .append(TaskParameters.toHdr(sep)).append(sep)
                .append("t_created").append(sep)
                .append("t_start_time").append(sep)
                .append("t_interval_sec").append(sep)
                .append("t_priority").append(sep)
                .append("t_tag").append(sep)
                .append("t_end_time").append(sep)
                .append("t_type").append(sep)
                .append("t_id");
        return(sb.toString());
    }

    public static String toNullTsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep).append(sep)
                .append(TaskParameters.toNullTsv(sep)).append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep)
                .append(sep);
        return(sb.toString());
    }

    public String toCsv(String sep) throws NullPointerException {
        StringBuffer sb = new StringBuffer()
                .append(count).append(sep)
                .append(filter).append(sep)
                .append(parameters.toCsv(sep)).append(sep)
                .append(created).append(sep)
                .append(start_time).append(sep)
                .append(interval_sec).append(sep)
                .append(priority).append(sep)
                .append(tag).append(sep)
                .append(end_time).append(sep)
                .append(type).append(sep)
                .append(id);
        return(sb.toString());
    }

}

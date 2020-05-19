package ie.ncirl.sspproject.dataimport.measurement.http;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Task extends Measurement {

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

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "t_count", sep);
        separate(sb, "t_filter", sep);
        separate(sb, (new TaskParameters()).toHdr(sep), sep);
        separate(sb, "t_created", sep);
        separate(sb, "t_start_time", sep);
        separate(sb, "t_interval_sec", sep);
        separate(sb, "t_priority", sep);
        separate(sb, "t_tag", sep);
        separate(sb, "t_end_time", sep);
        separate(sb, "t_type", sep);
        separate(sb, "t_id", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws NullPointerException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, filter, quote, sep);

        separate(sb, parameters != null
                ? parameters.toCsv(nullValue, quote, sep)
                : (new TaskParameters()).toNullCsv(nullValue, sep), sep);

        quoteAndSeparate(sb, created, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, tag, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, id, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // filter
        separate(sb, (new TaskParameters()).toNullCsv(nullValue, sep), sep); // parameters
        separate(sb, nullValue, sep); // created
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // tag
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, NO_SEP); // id
        return(sb.toString());
    }
}

package ie.ncirl.diaproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

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
    public String toHdr(String sep) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        return null;
    }
}

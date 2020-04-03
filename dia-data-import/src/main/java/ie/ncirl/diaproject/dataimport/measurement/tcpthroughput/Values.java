package ie.ncirl.diaproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class Values extends Measurement {
    public String throught;
    public String speed;
    public String error;
    public String speedAll;
    public String speedDisplay;
    public String duration;
    public String data_limit_exceeded;
    public String server_version;
    public String tcp_speed_results;
    public String total_data_sent_received;
    public String context_results;
    public String mptcp_config;

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

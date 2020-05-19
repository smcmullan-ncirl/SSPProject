package ie.ncirl.sspproject.dataimport.measurement.tcpthroughput;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

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
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_throught", sep);
        separate(sb, "v_speed", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_speedAll", sep);
        separate(sb, "v_speedDisplay", sep);
        separate(sb, "v_duration", sep);
        separate(sb, "v_data_limit_exceeded", sep);
        separate(sb, "v_server_version", sep);
        separate(sb, "v_tcp_speed_results", sep);
        separate(sb, "v_total_data_sent_received", sep);
        separate(sb, "v_context_results", sep);
        separate(sb, "v_mptcp_config", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, throught, quote, sep);
        quoteAndSeparate(sb, speed, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, speedAll, quote, sep);
        quoteAndSeparate(sb, speedDisplay, quote, sep);
        quoteAndSeparate(sb, duration, quote, sep);
        quoteAndSeparate(sb, data_limit_exceeded, quote, sep);
        quoteAndSeparate(sb, server_version, quote, sep);
        quoteAndSeparate(sb, tcp_speed_results, quote, sep);
        quoteAndSeparate(sb, total_data_sent_received, quote, sep);
        quoteAndSeparate(sb, context_results, quote, sep);
        quoteAndSeparate(sb, mptcp_config, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // throught
        separate(sb, nullValue, sep); // speed
        separate(sb, nullValue, sep); // error
        separate(sb, nullValue, sep); // speedAll
        separate(sb, nullValue, sep); // speedDisplay
        separate(sb, nullValue, sep); // duration
        separate(sb, nullValue, sep); // data_limit_exceeded
        separate(sb, nullValue, sep); // server_version
        separate(sb, nullValue, sep); // tcp_speed_results
        separate(sb, nullValue, sep); // total_data_sent_received
        separate(sb, nullValue, sep); // context_results
        separate(sb, nullValue, NO_SEP); // mptcp_config
        return(sb.toString());
    }
}

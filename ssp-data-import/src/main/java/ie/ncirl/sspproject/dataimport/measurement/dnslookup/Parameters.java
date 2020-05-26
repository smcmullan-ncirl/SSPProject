package ie.ncirl.sspproject.dataimport.measurement.dnslookup;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Parameters extends Measurement {

    public String count;
    public String target;
    public String parameters;
    public String start_time;
    public String interval_sec;
    public String server;
    public String priority;
    public String end_time;
    public String key;
    public String type;
    public String context_interval_sec;
    public String has_multi_server;
    public String servers;
    public String sensitive;
    public String qclass;
    public String qtype;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, "p_count", sep);
        separate(sb, "p_target", sep);
        separate(sb, "p_parameters", sep);
        separate(sb, "p_start_time", sep);
        separate(sb, "p_interval_sec", sep);
        separate(sb, "p_server", sep);
        separate(sb, "p_priority", sep);
        separate(sb, "p_end_time", sep);
        separate(sb, "p_key", sep);
        separate(sb, "p_type", sep);
        separate(sb, "p_context_interval_sec", sep);
        separate(sb, "p_has_multi_server", sep);
        separate(sb, "p_servers", sep);
        separate(sb, "p_sensitive", sep);
        separate(sb, "p_qclass", sep);
        separate(sb, "p_qtype", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, count, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, parameters, quote, sep);
        quoteAndSeparate(sb, start_time, quote, sep);
        quoteAndSeparate(sb, interval_sec, quote, sep);
        quoteAndSeparate(sb, server, quote, sep);
        quoteAndSeparate(sb, priority, quote, sep);
        quoteAndSeparate(sb, end_time, quote, sep);
        quoteAndSeparate(sb, key, quote, sep);
        quoteAndSeparate(sb, type, quote, sep);
        quoteAndSeparate(sb, context_interval_sec, quote, sep);
        quoteAndSeparate(sb, has_multi_server, quote, sep);
        quoteAndSeparate(sb, servers, quote, sep);
        quoteAndSeparate(sb, sensitive, quote, sep);
        quoteAndSeparate(sb, qclass, quote, sep);
        quoteAndSeparate(sb, qtype, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // count
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // parameters
        separate(sb, nullValue, sep); // start_time
        separate(sb, nullValue, sep); // interval_sec
        separate(sb, nullValue, sep); // server
        separate(sb, nullValue, sep); // priority
        separate(sb, nullValue, sep); // end_time
        separate(sb, nullValue, sep); // key
        separate(sb, nullValue, sep); // type
        separate(sb, nullValue, sep); // context_interval_sec
        separate(sb, nullValue, sep); // has_multi_server
        separate(sb, nullValue, sep); // servers
        separate(sb, nullValue, sep); // sensitive
        separate(sb, nullValue, sep); // qclass
        separate(sb, nullValue, NO_SEP); // qtype
        return(sb.toString());
    }
}

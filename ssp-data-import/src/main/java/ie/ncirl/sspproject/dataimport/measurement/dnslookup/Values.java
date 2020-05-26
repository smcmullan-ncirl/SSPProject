package ie.ncirl.sspproject.dataimport.measurement.dnslookup;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Values extends Measurement {
    public String time_ms;
    public String CalledByLocation;
    public String MeasurementLatitude;
    public String real_hostname;
    public String address;
    public String MeasurementLongitude;
    public String error;
    public String context_results;
    public String qclass;
    public String qtype;
    public String results;
    public String target;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_time_ms", sep);
        separate(sb, "v_CalledByLocation", sep);
        separate(sb, "v_MeasurementLatitude", sep);
        separate(sb, "v_real_hostname", sep);
        separate(sb, "v_address", sep);
        separate(sb, "v_MeasurementLongitude", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_context_results", sep);
        separate(sb, "v_qclass", sep);
        separate(sb, "v_qtype", sep);
        separate(sb, "v_target", sep);
        separate(sb, "v_results", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, time_ms, quote, sep);
        quoteAndSeparate(sb, CalledByLocation, quote, sep);
        quoteAndSeparate(sb, MeasurementLatitude, quote, sep);
        quoteAndSeparate(sb, real_hostname, quote, sep);
        quoteAndSeparate(sb, address, quote, sep);
        quoteAndSeparate(sb, MeasurementLongitude, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, context_results, quote, sep);
        quoteAndSeparate(sb, qclass, quote, sep);
        quoteAndSeparate(sb, qtype, quote, sep);
        quoteAndSeparate(sb, results, quote, sep);
        quoteAndSeparate(sb, target, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // time_ms
        separate(sb, nullValue, sep); // CalledByLocation
        separate(sb, nullValue, sep); // MeasurementLatitude
        separate(sb, nullValue, sep); // real_hostname
        separate(sb, nullValue, sep); // address
        separate(sb, nullValue, sep); // MeasurementLongitude
        separate(sb, nullValue, sep); // error
        separate(sb, nullValue, sep); // context_results
        separate(sb, nullValue, sep); // qclass
        separate(sb, nullValue, sep); // qtype
        separate(sb, nullValue, sep); // results
        separate(sb, nullValue, NO_SEP); // target
        return(sb.toString());
    }
}

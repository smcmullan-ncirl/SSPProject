package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Values extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(Values.class);

    public String body;
    public String code;
    public String MeasurementLongitude;
    public String body_len;
    public String CalledByLocation;
    public String time_ms;
    public String MeasurementLatitude;
    public String headers;
    public String headers_len;
    public String error;
    public String MobilePktRecv;
    public String currentBatteryLevel;
    public String MobileBytesRecv;
    public String contextTimestamp;
    public String MobileBytesSend;
    public String currentRssi;
    public String MobilePktSend;
    public String contextMeasurementInterval;
    public String context_results;
    public String content_length;
    public String status_code;
    public String samples;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_body", sep);
        separate(sb, "v_code", sep);
        separate(sb, "v_MeasurementLongitude", sep);
        separate(sb, "v_body_len", sep);
        separate(sb, "v_CalledByLocation", sep);
        separate(sb, "v_time_ms", sep);
        separate(sb, "v_MeasurementLatitude", sep);
        separate(sb, "v_headers", sep);
        separate(sb, "v_headers_len", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_MobilePktRecv", sep);
        separate(sb, "v_currentBatteryLevel", sep);
        separate(sb, "v_MobileBytesRecv", sep);
        separate(sb, "v_contextTimestamp", sep);
        separate(sb, "v_MobileBytesSend", sep);
        separate(sb, "v_currentRssi", sep);
        separate(sb, "v_MobilePktSend", sep);
        separate(sb, "v_contextMeasurementInterval", sep);
        separate(sb, "v_context_results", sep);
        separate(sb, "v_content_length", sep);
        separate(sb, "v_status_code", sep);
        separate(sb, "v_samples", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, body, quote, sep);
        quoteAndSeparate(sb, code, quote, sep);
        quoteAndSeparate(sb, MeasurementLongitude, quote, sep);
        quoteAndSeparate(sb, body_len, quote, sep);
        quoteAndSeparate(sb, CalledByLocation, quote, sep);
        quoteAndSeparate(sb, time_ms, quote, sep);
        quoteAndSeparate(sb, MeasurementLatitude, quote, sep);
        quoteAndSeparate(sb, headers, quote, sep);
        quoteAndSeparate(sb, headers_len, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, MobilePktRecv, quote, sep);
        quoteAndSeparate(sb, currentBatteryLevel, quote, sep);
        quoteAndSeparate(sb, MobileBytesRecv, quote, sep);
        quoteAndSeparate(sb, contextTimestamp, quote, sep);
        quoteAndSeparate(sb, MobileBytesSend, quote, sep);
        quoteAndSeparate(sb, currentRssi, quote, sep);
        quoteAndSeparate(sb, MobilePktSend, quote, sep);
        quoteAndSeparate(sb, contextMeasurementInterval, quote, sep);
        quoteAndSeparate(sb, context_results, quote, sep);
        quoteAndSeparate(sb, content_length, quote, sep);
        quoteAndSeparate(sb, status_code, quote, sep);
        quoteAndSeparate(sb, samples, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // body
        separate(sb, nullValue, sep); // code
        separate(sb, nullValue, sep); // MeasurementLongitude
        separate(sb, nullValue, sep); // body_len
        separate(sb, nullValue, sep); // CalledByLocation
        separate(sb, nullValue, sep); // time_ms
        separate(sb, nullValue, sep); // MeasurementLatitude
        separate(sb, nullValue, sep); // headers
        separate(sb, nullValue, sep); // headers_len
        separate(sb, nullValue, sep); // error
        separate(sb, nullValue, sep); // MobilePktRecv
        separate(sb, nullValue, sep); // currentBatteryLevel
        separate(sb, nullValue, sep); // MobileBytesRecv
        separate(sb, nullValue, sep); // contextTimestamp
        separate(sb, nullValue, sep); // MobileBytesSend
        separate(sb, nullValue, sep); // currentRssi
        separate(sb, nullValue, sep); // MobilePktSend
        separate(sb, nullValue, sep); // contextMeasurementInterval
        separate(sb, nullValue, sep); // context_results
        separate(sb, nullValue, sep); // content_length
        separate(sb, nullValue, sep); // status_code
        separate(sb, nullValue, NO_SEP); // samples
        return(sb.toString());
    }
}

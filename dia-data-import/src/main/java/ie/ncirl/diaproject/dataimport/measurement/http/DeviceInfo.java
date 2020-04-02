package ie.ncirl.diaproject.dataimport.measurement.http;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceInfo extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(DeviceInfo.class);

    public String model;
    public String os;
    public String manufacturer;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "di_model", sep);
        separate(sb, "di_os", sep);
        separate(sb, "di_manufacturer", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, model, quote, sep);
        quoteAndSeparate(sb, os, quote, sep);
        quoteAndSeparate(sb, manufacturer, quote, NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // model
        separate(sb, nullValue, sep); // os
        separate(sb, nullValue, NO_SEP); // manufacturerr
        return(sb.toString());
    }
}

package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class DeviceInfo extends Measurement {

    public String model;
    public String os;
    public String manufacturer;
    public String tac;
    public String id;
    public String user;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "di_model", sep);
        separate(sb, "di_os", sep);
        separate(sb, "di_manufacturer", sep);
        separate(sb, "di_tac", sep);
        separate(sb, "di_id", sep);
        separate(sb, "di_user", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, model, quote, sep);
        quoteAndSeparate(sb, os, quote, sep);
        quoteAndSeparate(sb, manufacturer, quote, sep);
        quoteAndSeparate(sb, tac, quote, sep);
        quoteAndSeparate(sb, id, quote, sep);
        quoteAndSeparate(sb, user, quote, NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep) // model
                .append(sep) // os
                .append(sep) // manufacturer
                .append(sep) // tac
                .append(sep) // id
                .append(NO_SEP); // user
        return(sb.toString());
    }
}

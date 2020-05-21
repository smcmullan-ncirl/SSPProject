package ie.ncirl.sspproject.dataimport.measurement.udpburst;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Values extends Measurement {
    public String target_ip;
    public String PRR;
    public String error;
    public String jitter;
    public String Inversion_Number;
    public String inversion_number;
    public String loss_rate;
    public String loss_ratio;
    public String out_of_order_ratio;
    public String context_results;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_target_ip", sep);
        separate(sb, "v_PRR", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_jitter", sep);
        separate(sb, "v_Inversion_Number", sep);
        separate(sb, "v_inversion_number", sep);
        separate(sb, "v_loss_rate", sep);
        separate(sb, "v_loss_ratio", sep);
        separate(sb, "v_out_of_order_ratio", sep);
        separate(sb, "v_context_results", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, target_ip, quote, sep);
        quoteAndSeparate(sb, PRR, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, jitter, quote, sep);
        quoteAndSeparate(sb, Inversion_Number, quote, sep);
        quoteAndSeparate(sb, inversion_number, quote, sep);
        quoteAndSeparate(sb, loss_rate, quote, sep);
        quoteAndSeparate(sb, loss_ratio, quote, sep);
        quoteAndSeparate(sb, out_of_order_ratio, quote, sep);
        quoteAndSeparate(sb, context_results, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // target_ip
        separate(sb, nullValue, sep); // PRR
        separate(sb, nullValue, sep); // error
        separate(sb, nullValue, sep); // jitter
        separate(sb, nullValue, sep); // Inversion_Number
        separate(sb, nullValue, sep); // inversion_number
        separate(sb, nullValue, sep); // loss_rate
        separate(sb, nullValue, sep); // loss_ratio
        separate(sb, nullValue, sep); // out_of_order_ratio
        separate(sb, nullValue, NO_SEP); // context_results
        return(sb.toString());
    }
}

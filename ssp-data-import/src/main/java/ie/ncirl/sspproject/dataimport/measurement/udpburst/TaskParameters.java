package ie.ncirl.sspproject.dataimport.measurement.udpburst;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class TaskParameters extends Measurement {

    public String packet_size_byte;
    public String direction;
    public String udp_interval;
    public String target;
    public String packet_burst;

    @Override
    public String toHdr(String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_packet_size_byte", sep);
        separate(sb, "tp_direction", sep);
        separate(sb, "tp_udp_interval", sep);
        separate(sb, "tp_target", sep);
        separate(sb, "tp_packet_burst", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, packet_size_byte, quote, sep);
        quoteAndSeparate(sb, direction, quote, sep);
        quoteAndSeparate(sb, udp_interval, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, packet_burst, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // packet_size_byte
        separate(sb, nullValue, sep); // direction
        separate(sb, nullValue, sep); // udp_interval
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, NO_SEP); // packet_burst
        return(sb.toString());
    }
}

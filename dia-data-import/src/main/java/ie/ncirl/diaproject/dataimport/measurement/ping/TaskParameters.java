package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;

public class TaskParameters extends Measurement {

    public String location_update_distance;
    public String target;
    public String trigger_location_update;
    public String packet_size_byte;
    public String ping_timeout_sec;
    public String profile_1_freq;
    public String profile_2_freq;
    public String profile_3_freq;
    public String profile_4_freq;
    public String profile_unlimited;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "tp_location_update_distance", sep);
        separate(sb, "tp_target", sep);
        separate(sb, "tp_trigger_location_update", sep);
        separate(sb, "tp_packet_size_byte", sep);
        separate(sb, "tp_ping_timeout_sec", sep);
        separate(sb, "tp_profile_1_freq", sep);
        separate(sb, "tp_profile_2_freq", sep);
        separate(sb, "tp_profile_3_freq", sep);
        separate(sb, "tp_profile_4_freq", sep);
        separate(sb, "tp_profile_unlimited", NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toCsv(String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, location_update_distance, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, trigger_location_update, quote, sep);
        quoteAndSeparate(sb, packet_size_byte, quote, sep);
        quoteAndSeparate(sb, ping_timeout_sec, quote, sep);
        quoteAndSeparate(sb, profile_1_freq, quote, sep);
        quoteAndSeparate(sb, profile_2_freq, quote, sep);
        quoteAndSeparate(sb, profile_3_freq, quote, sep);
        quoteAndSeparate(sb, profile_4_freq, quote, sep);
        quoteAndSeparate(sb, profile_unlimited, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(sep) // location_update_distance
                .append(sep) // target
                .append(sep) // trigger_location_update
                .append(sep) // packet_size_byte
                .append(sep) // ping_timeout_sec
                .append(sep) // profile_1_freq
                .append(sep) // profile_2_freq
                .append(sep) // profile_3_freq
                .append(sep) // profile_4_freq
                .append(NO_SEP); // profile_unlimited
        return(sb.toString());
    }
}

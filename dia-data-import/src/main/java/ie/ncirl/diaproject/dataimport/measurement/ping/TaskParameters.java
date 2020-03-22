package ie.ncirl.diaproject.dataimport.measurement.ping;

import ie.ncirl.diaproject.dataimport.measurement.Measurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskParameters extends Measurement {
    private static Logger logger = LoggerFactory.getLogger(TaskParameters.class);

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
    public String toCsv(String nullValue, String quote, String sep) {
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
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // location_update_distance
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // trigger_location_update
        separate(sb, nullValue, sep); // packet_size_byte
        separate(sb, nullValue, sep); // ping_timeout_sec
        separate(sb, nullValue, sep); // profile_1_freq
        separate(sb, nullValue, sep); // profile_2_freq
        separate(sb, nullValue, sep); // profile_3_freq
        separate(sb, nullValue, sep); // profile_4_freq
        separate(sb, nullValue, NO_SEP); // profile_unlimited
        return(sb.toString());
    }
}

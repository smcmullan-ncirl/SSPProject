package ie.ncirl.diaproject.dataimport.measurement.ping;

public class Location {

    public String latitude;
    public String longitude;

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("loc_latitude").append(sep)
                .append("loc_longitude");
        return(sb.toString());
    }

    public String toCsv(String sep, String quote) {
        StringBuffer sb = new StringBuffer()
                .append(latitude).append(sep)
                .append(longitude);
        return(sb.toString());
    }

}

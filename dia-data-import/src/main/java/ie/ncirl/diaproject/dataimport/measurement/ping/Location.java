package ie.ncirl.diaproject.dataimport.measurement.ping;

public class Location {

    public String latitude;
    public String longitude;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("latitude").append("\t")
                .append("longitude");
        return(sb.toString());
    }

    public String toTsv() {
        StringBuffer sb = new StringBuffer()
                .append(latitude).append("\t")
                .append(longitude);
        return(sb.toString());
    }

}

package ie.ncirl.diaproject.dataimport.measurement;

public class Location {
    public String latitude;
    public String longitude;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("latitude").append("\t")
                .append("longitude");
        return(sb.toString());
    }

    public String toTSV() {
        StringBuffer sb = new StringBuffer()
                .append(latitude).append("\t")
                .append(longitude);
        return(sb.toString());
    }
}

package ie.ncirl.diaproject.dataimport.measurement.ping;

public class DeviceInfo {

    public String model;
    public String os;
    public String manufacturer;
    public String tac;
    public String id;
    public String user;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("model").append("\t")
                .append("os").append("\t")
                .append("manufacturer").append("\t")
                .append("tac").append("\t")
                .append("id").append("\t")
                .append("user");
        return(sb.toString());
    }

    public String toTsv() {
        StringBuffer sb = new StringBuffer()
                .append(model).append("\t")
                .append(os).append("\t")
                .append(manufacturer).append("\t")
                .append(tac).append("\t")
                .append(id).append("\t")
                .append(user);
        return(sb.toString());
    }

}

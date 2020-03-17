package ie.ncirl.diaproject.dataimport.measurement;

public class DeviceInfo {
    public String model;
    public String os;
    public String manufacturer;
    public String tac;

    public static String toHdr() {
        StringBuffer sb = new StringBuffer()
                .append("model").append("\t")
                .append("os").append("\t")
                .append("manufacturer").append("\t")
                .append("tac");
        return(sb.toString());
    }

    public String toTSV() {
        StringBuffer sb = new StringBuffer()
                .append(model).append("\t")
                .append(os).append("\t")
                .append(manufacturer).append("\t")
                .append(tac);
        return(sb.toString());
    }
}

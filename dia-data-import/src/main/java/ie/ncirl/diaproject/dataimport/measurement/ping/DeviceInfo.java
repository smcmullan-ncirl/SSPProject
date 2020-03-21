package ie.ncirl.diaproject.dataimport.measurement.ping;

public class DeviceInfo {

    public String model;
    public String os;
    public String manufacturer;
    public String tac;
    public String id;
    public String user;

    public static String toHdr(String sep) {
        StringBuffer sb = new StringBuffer()
                .append("di_model").append(sep)
                .append("di_os").append(sep)
                .append("di_manufacturer").append(sep)
                .append("di_tac").append(sep)
                .append("di_id").append(sep)
                .append("di_user");
        return(sb.toString());
    }

    public String toCsv(String sep) {
        StringBuffer sb = new StringBuffer()
                .append(model).append(sep)
                .append(os).append(sep)
                .append(manufacturer).append(sep)
                .append(tac).append(sep)
                .append(id).append(sep)
                .append(user);
        return(sb.toString());
    }

}

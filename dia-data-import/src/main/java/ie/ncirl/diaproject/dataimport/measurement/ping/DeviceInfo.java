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

    public String toCsv(String sep, String quote) {
        StringBuffer sb = new StringBuffer()
                .append(quote).append(model).append(quote).append(sep)
                .append(quote).append(os).append(quote).append(sep)
                .append(quote).append(manufacturer).append(quote).append(sep)
                .append(quote).append(tac).append(quote).append(sep)
                .append(quote).append(id).append(quote).append(sep)
                .append(quote).append(user).append(quote);
        return(sb.toString());
    }

}

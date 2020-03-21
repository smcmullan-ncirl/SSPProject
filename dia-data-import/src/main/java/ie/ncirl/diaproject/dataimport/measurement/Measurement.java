package ie.ncirl.diaproject.dataimport.measurement;

abstract public class Measurement {
    public static final String TAB = "\t";
    public static final String COMMA = ",";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String SINGLE_QUOTE = "'";
    public static final String NO_QUOTE = "";

    abstract public String toHdr(String sep) throws UnsupportedOperationException;
    abstract public String toCsv(String sep, String quote) throws UnsupportedOperationException;

    public static StringBuffer quoteAndSeparate(StringBuffer sb, String field, String sep, String quote) {
        sb.append(quote).append(field).append(quote).append(sep);
        return sb;
    }
    public static StringBuffer quoteALastField(StringBuffer sb, String field, String quote) {
        sb.append(quote).append(field).append(quote);
        return sb;
    }
}

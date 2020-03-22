package ie.ncirl.diaproject.dataimport.measurement;

abstract public class Measurement {
    public static final String NULL = null;
    public static final String NO_NULL = "";

    public static final String DOUBLE_QUOTE = "\"";
    public static final String SINGLE_QUOTE = "'";
    public static final String NO_QUOTE = "";

    public static final String TAB = "\t";
    public static final String COMMA = ",";
    public static final String NO_SEP = "";

    abstract public String toHdr(String sep) throws UnsupportedOperationException;
    abstract public String toCsv(String nullValue, String quote, String sep) throws UnsupportedOperationException;
    abstract public String toNullCsv(String nullValue, String sep) throws UnsupportedOperationException;

    public static StringBuffer quoteAndSeparate(StringBuffer sb, String field, String quote, String sep) {
        if(field == null) {
            sb.append(field).append(sep);
        } else {
            sb.append(quote).append(field).append(quote).append(sep);
        }
        return sb;
    }

    public static StringBuffer quote(StringBuffer sb, String field, String quote) {
        if(field == null) {
            sb.append(field);
        } else {
            sb.append(quote).append(field).append(quote);
        }
        return sb;
    }

    public static StringBuffer separate(StringBuffer sb, String field, String sep) {
        sb.append(field).append(sep);
        return sb;
    }
}

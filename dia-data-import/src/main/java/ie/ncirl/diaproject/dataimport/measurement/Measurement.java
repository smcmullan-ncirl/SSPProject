package ie.ncirl.diaproject.dataimport.measurement;

abstract public class Measurement {
    public static final String TAB = "\t";
    public static final String COMMA = ",";

    abstract public String toHdr(String sep) throws UnsupportedOperationException;
    abstract public String toCsv(String sep) throws UnsupportedOperationException;
}

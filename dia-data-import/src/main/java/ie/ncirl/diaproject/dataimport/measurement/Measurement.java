package ie.ncirl.diaproject.dataimport.measurement;

public abstract class Measurement {
    public abstract String toHdr() throws UnsupportedOperationException;
    public abstract String toTsv() throws UnsupportedOperationException;
}

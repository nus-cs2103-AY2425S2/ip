package nikingoda.NikingodaException;

public class NikingodaException extends Exception {
    /**
     * throw exception with custom messages for particular situation
     * used for handle some particular error/invalid syntax
     *
     * @param s error message
     */
    public NikingodaException(String s) {
        super(s);
    }
}

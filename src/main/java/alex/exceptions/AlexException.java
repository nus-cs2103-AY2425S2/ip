package alex.exceptions;

/**
 * General Exception for all exceptions from the Alex program
 */
public class AlexException extends Exception {
    public AlexException(String errorMsg) {
        super(errorMsg);
    }
}

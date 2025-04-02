package orim.myexception;

/**
 * Indicates the input by user is invalid.
 */
public class IncorrectInputException extends MyException {
    public IncorrectInputException(String message) {
        super(message);
    }
}

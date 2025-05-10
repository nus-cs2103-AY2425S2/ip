package helix.exception;

/**
 * Signals that an invalid date format has been encountered.
 */
public class InvalidDateFormatException extends HelixException {

    /**
     * Constructs an InvalidDateFormatException with a detailed error message.
     *
     * @param message the error message
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}

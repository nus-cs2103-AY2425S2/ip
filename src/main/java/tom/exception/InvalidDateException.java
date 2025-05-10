package tom.exception;

/**
 * Exception thrown when an invalid date is encountered.
 */
public class InvalidDateException extends TomCommandException {

    /**
     * Constructs an InvalidDateException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}

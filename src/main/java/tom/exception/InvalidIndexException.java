package tom.exception;

/**
 * Exception thrown when an invalid index is encountered.
 */
public class InvalidIndexException extends TomCommandException {

    /**
     * Constructs an InvalidIndexException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidIndexException(String message) {
        super(message);
    }
}

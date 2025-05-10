package tom.exception;

/**
 * Exception thrown when an error during command execution is encountered.
 */
public class TomCommandException extends TomException {

    /**
     * Constructs an TomCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TomCommandException(String message) {
        super(message);
    }
}

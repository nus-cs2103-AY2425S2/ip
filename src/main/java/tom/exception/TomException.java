package tom.exception;

/**
 * Base exception class for all custom exceptions in the application.
 */
public class TomException extends Exception {

    /**
     * Constructs a TomException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TomException(String message) {
        super(message);
    }
}

package phil.exception;

/**
 * Represents a runtime exception, specially for Phil.
 */
public class PhilException extends RuntimeException {

    /**
     * Sets up the PhilException.
     *
     * @param message representing message of exception.
     */
    public PhilException(String message) {
        super(message);
    }
}

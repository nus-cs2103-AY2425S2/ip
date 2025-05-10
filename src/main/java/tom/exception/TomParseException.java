package tom.exception;

/**
 * Exception thrown when an error during parsing is encountered.
 */
public class TomParseException extends TomException {

    /**
     * Constructs an ParseException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TomParseException(String message) {
        super(message);
    }

    public boolean needPrompt() {
        return false;
    }
}

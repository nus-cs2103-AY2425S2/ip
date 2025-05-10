package tom.exception;

/**
 * Exception thrown when an incomplete command is encountered.
 */
public class IncompleteParseException extends TomParseException {

    /**
     * Constructs an IncompleteCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public IncompleteParseException(String message) {
        super(message);
    }

    public boolean needPrompt() {
        return true;
    }
}

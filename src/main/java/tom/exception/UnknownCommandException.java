package tom.exception;

/**
 * Exception thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends TomParseException {

    /**
     * Constructs an UnknownCommandException with the specified detail message.
     *
     */
    public UnknownCommandException() {
        super("Invalid Command! Please enter a valid command.");
    }
}

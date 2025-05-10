package steve.exceptions;

/**
 * Exception thrown when an invalid command is entered.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command entered.");
    }

    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

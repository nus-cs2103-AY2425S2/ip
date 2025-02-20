package exception;

/**
 * Represents an exception thrown when an unknown command is entered.
 */
public class UnknownCommandException extends BaimiException {
    public UnknownCommandException() {
        super("Unknown command. Please enter a valid command (e.g., todo, deadline, event, list, delete).");
    }
}

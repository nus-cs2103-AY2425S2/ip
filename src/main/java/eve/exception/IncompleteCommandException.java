package eve.exception;

/**
 * Represents an exception caused by an incomplete command.
 * It contains a custom error message to be displayed on the user interface.
 */
public class IncompleteCommandException extends EveException {
    public IncompleteCommandException() {
        super("Oh no... You are missing important information in your command.");
    }
}

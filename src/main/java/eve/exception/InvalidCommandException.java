package eve.exception;

/**
 * Represents an exception caused by an invalid command.
 * It contains a custom error message to be displayed on the user interface.
 */
public class InvalidCommandException extends EveException {
    public InvalidCommandException() {
        super("Oh no... I have no idea what you just said. Please try again...");
    }
}

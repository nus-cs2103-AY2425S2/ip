package eve.exception;

/**
 * Represents an exception caused by an incomplete description for events.
 * It contains a custom error message to be displayed on the user interface.
 */
public class InvalidEventException extends EveException {
    public InvalidEventException() {
        super("Let me know the time of the event using keywords /from and /to");
    }
}

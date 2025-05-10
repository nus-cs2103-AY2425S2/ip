package eve.exception;

/**
 * Represents an exception caused by an incomplete description for deadlines.
 * It contains a custom error message to be displayed on the user interface.
 */
public class InvalidDeadlineException extends EveException {
    public InvalidDeadlineException() {
        super("Let me know the deadline using keyword /by");
    }
}

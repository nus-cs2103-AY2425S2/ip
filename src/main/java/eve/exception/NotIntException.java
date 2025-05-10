package eve.exception;

/**
 * Represents an exception caused by an invalid input when a number is expected.
 * It contains a custom error message to be displayed on the user interface.
 */
public class NotIntException extends EveException {
    public NotIntException() {
        super("You should input a task number!");
    }
}

package eve.exception;

/**
 * Represents an exception caused by an invalid date and time input by user.
 * It contains a custom error message to be displayed on the user interface.
 */
public class InvalidDateTimeException extends EveException {
    public InvalidDateTimeException() {
        super("You should let me know the date and time using the format dd/mm/yyyy hh:mm");
    }
}

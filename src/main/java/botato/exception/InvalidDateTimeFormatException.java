package botato.exception;

/**
 * This exception is thrown when an invalid date and time format is inputted when a date and time is expected.
 */
public class InvalidDateTimeFormatException extends BotatoException {
    public InvalidDateTimeFormatException() {
        super("I didn't catch that date... Say 'help' if you don't know how you should type it!");
    }
}

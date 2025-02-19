package exceptions;

/**
 * Concrete class that is thrown when the format of a required datetime is wrong
 */
public class DateTimeFormatException extends ThoughtBotException {
    /**
     * Constructor for DateTimeFormatException class
     */
    public DateTimeFormatException() {
        super("The date and time are formatted wrongly.\n"
                + "The correct format is as follows:\n"
                + "YYYY-MM-DD HH:MM");
    }
}

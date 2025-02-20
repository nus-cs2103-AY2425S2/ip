package amara.exceptions;

/**
 * A custom exception class for handling errors specific to the Amara application.
 * <p>
 * This exception provides static factory methods to create predefined error messages
 * for common error scenarios, such as invalid commands, incorrect parameters,
 * or file write issues.
 * </p>
 */
public class AmaraException extends Exception {
    private static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know "
            + "what that means (Type <help> to see available commands). :(";
    private static final String NUMBER_FORMAT = "OOPS!!! The word that you entered is "
            + "not a number or empty. :(";
    private static final String WRITE_FILE = "OOPS!!! I cannot write to a file at the "
            + "moment, try again later. :(";
    private static final String FORMAT = "OOPS!!! The format for "
            + "%s parameter(s) is/are empty!\n  Usage: %s <description> %s %s";
    private static final String OUT_OF_BOUNDS = "OOPS!!! The index you want to "
            + "mark/unmark/delete is out of bounds. :(";
    private static final String DATE_TIME_FORMAT = "OOPS!!! I don't understand the "
            + "time/date format that you have given me :(\n  (Format: YYYY-MM-DD HHMM)";
    private static final String FILE_FORMAT = "OOPS!!! Format in the saved file is wrong :(\n";

    public AmaraException(String message) {
        super(message);
    }

    public static AmaraException invalidCommand() {
        return new AmaraException(ERROR_MESSAGE);
    }

    public static AmaraException invalidToDoParameter() {
        return new AmaraException(String.format(FORMAT, "todo", "todo", "", ""));
    }

    public static AmaraException invalidDeadlineParameter() {
        return new AmaraException(String.format(FORMAT, "deadline", "deadline", "/by <time>", ""));
    }

    public static AmaraException invalidEventParameter() {
        return new AmaraException(String.format(FORMAT, "event", "event", "/from <start time>", "/to <end time>"));
    }

    public static AmaraException indexOutOfBounds() {
        return new AmaraException(OUT_OF_BOUNDS);
    }

    public static AmaraException numberFormatting() {
        return new AmaraException(NUMBER_FORMAT);
    }

    public static AmaraException fileWriteException() {
        return new AmaraException(WRITE_FILE);
    }

    public static AmaraException dateTimeFormatException() {
        return new AmaraException(DATE_TIME_FORMAT);
    }

    public static AmaraException fileFormatException() {
        return new AmaraException(FILE_FORMAT);
    }
}

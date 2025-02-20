package adam.exceptions;

import adam.parser.Parser;

/**
 * Represents an exception where the date format is invalid.
 */
public class InvalidDate extends AdamException {
    private static String defaultErrorMsg = "Date format is invalid! Please use the format " + Parser.DATE_FORMAT_STRING
            + ".";

    public InvalidDate() {
        super(InvalidDate.defaultErrorMsg);
    }
}

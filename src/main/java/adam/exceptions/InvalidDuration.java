package adam.exceptions;

/**
 * Represents an exception where the date format is invalid.
 */
public class InvalidDuration extends AdamException {
    private static final String defaultErrorMsg =
            "Duration format is invalid. Please use the format: Xh Ym, such as 2h 3m";

    public InvalidDuration() {
        super(InvalidDuration.defaultErrorMsg);
    }
}

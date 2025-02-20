package adam.exceptions;

/**
 * Represents an exception where the log file format is invalid.
 */
public class InvalidLogFile extends AdamException {
    private static String defaultErrorMsg = "Log file format is invalid!";

    public InvalidLogFile() {
        super(InvalidLogFile.defaultErrorMsg);
    }
}

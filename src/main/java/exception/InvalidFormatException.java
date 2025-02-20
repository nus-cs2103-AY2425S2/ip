package exception;

/**
 * Represents an exception thrown when the user input does not match the expected format.
 */
public class InvalidFormatException extends BaimiException {
    public InvalidFormatException(String correctFormat) {
        super("Invalid format. Correct format: " + correctFormat);
    }
}

package doot;

/**
 * Exception raised when the format is incorrect
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super(message);
    }
}

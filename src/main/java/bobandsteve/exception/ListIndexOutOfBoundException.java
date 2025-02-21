package bobandsteve.exception;

/**
 * Custom exception class for handling situations where an invalid index is accessed
 * in a list in the BobAndSteve application.
 * This exception is thrown when a list index is out of bounds.
 */
public class ListIndexOutOfBoundException extends BobAndSteveException {
    public ListIndexOutOfBoundException(String message) {
        super(message);
    }
}

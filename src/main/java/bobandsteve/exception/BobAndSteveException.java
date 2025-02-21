package bobandsteve.exception;

/**
 * Custom exception class for the BobAndSteve application.
 * This exception is thrown when there is a specific error related to the application logic.
 */
public class BobAndSteveException extends Exception {
    public BobAndSteveException(String message) {
        super(message);
    }
}

package bobandsteve.exception;

/**
 * Custom exception class for handling invalid commands in the BobAndSteve application.
 * This exception is thrown when an invalid or unrecognized command is entered by the user.
 */
public class InvalidCommandException extends BobAndSteveException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

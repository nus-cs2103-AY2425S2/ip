package bobandsteve.exception;

/**
 * Custom exception class for handling invalid command formats in the BobAndSteve application.
 * This exception is thrown when a user enters a command in an incorrect format.
 */
public class InvalidCommandFormatException extends BobAndSteveException {
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}

package boink.exceptions;

/**
 * This class represents an exception thrown when user command is invalid.
 */

public class InvalidCommandException extends BoinkException {

    /**
     * Constructor for InvalidTaskInputException.
     * @param message Message for Exception.
     */

    public InvalidCommandException(String message) {
        super(message);
    }
}

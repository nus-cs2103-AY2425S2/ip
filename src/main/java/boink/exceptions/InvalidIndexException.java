package boink.exceptions;

/**
 * This class represents an exception thrown when invalid index is given for user command.
 */

public class InvalidIndexException extends BoinkException {

    /**
     * Constructor for InvalidTaskInputException.
     * @param message Message for Exception.
     */

    public InvalidIndexException(String message) {
        super(message);
    }
}

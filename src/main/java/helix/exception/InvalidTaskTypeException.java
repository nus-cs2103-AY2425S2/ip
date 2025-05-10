package helix.exception;

/**
 * Signals that an invalid task type has been encountered.
 */
public class InvalidTaskTypeException extends HelixException {

    /**
     * Constructs an InvalidTaskTypeException with a default error message.
     */
    public InvalidTaskTypeException() {
        super("Invalid task type. Supported types: todo, deadline, event.");
    }

    /**
     * Constructs an InvalidTaskTypeException with a specific error message.
     *
     * @param message The detailed error message.
     */
    public InvalidTaskTypeException(String message) {
        super(message);
    }
}

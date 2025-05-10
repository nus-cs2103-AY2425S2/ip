package steve.exceptions;

/**
 * Exception thrown when an invalid task number is provided.
 */
public class InvalidTaskNumberException extends Exception {

    /**
     * Constructs a new InvalidTaskNumberException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}

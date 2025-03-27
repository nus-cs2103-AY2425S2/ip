package yapper.exceptions;

/**
 * Exception thrown when an invalid task type is encountered.
 */
public class InvalidTaskType extends RuntimeException {

    /**
     * Constructs a new {@code InvalidTaskType} exception with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidTaskType(String message) {
        super(message);
    }
}

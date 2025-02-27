package nyanko.command;

/**
 * Exception thrown when a task number provided by the user is invalid.
 * This occurs when the task number does not exist in the task list,
 * either because it is out of bounds or the task list is empty.
 */
public class InvalidTaskNumberException extends Exception {
    /**
     * Constructs an {@code InvalidTaskNumberException} with the specified error message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}

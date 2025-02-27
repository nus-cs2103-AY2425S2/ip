package nyanko.task;

/**
 * Represents an exception that is thrown when a task format is invalid.
 * This typically occurs when loading tasks from storage and the saved task
 * does not follow the expected format.
 */
public class InvalidTaskFormatException extends Exception {

    /**
     * Constructs an {@code InvalidTaskFormatException} with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidTaskFormatException(String message) {
        super(message);
    }
}

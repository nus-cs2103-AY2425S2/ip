package tom.exception;

/**
 * Exception thrown when a task fails to perform an operation.
 */
public class TaskException extends TomCommandException {

    /**
     * Constructs an InvalidIndexException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TaskException(String message) {
        super(message);
    }
}

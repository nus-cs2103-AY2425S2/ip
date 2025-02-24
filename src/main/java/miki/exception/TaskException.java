package miki.exception;

/**
 * Represents an exception that occurs when a task fails.
 */
public class TaskException extends MikiException {
    public TaskException() {
        super();
    }

    public TaskException(String message) {
        super(message);
    }
}

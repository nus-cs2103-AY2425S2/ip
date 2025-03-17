package taskmax.exception;

/**
 * Represents an exception specific to the Taskmax application.
 */
public class TaskmaxException extends Exception {

    /**
     * Constructs a TaskmaxException with the specified error message.
     *
     * @param message The error message to be displayed.
     */
    public TaskmaxException(String message) {
        super(message);
    }
}

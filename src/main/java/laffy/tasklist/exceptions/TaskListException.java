package laffy.tasklist.exceptions;

/**
 * Represents an exception that occurs while executing a command.
 */
public class TaskListException extends Exception {
    private static final String message = "[TASKL-ERROR]: An error occurred while executing the command.\n";

    public TaskListException(String explanation) {
        super(message + explanation);
    }
}

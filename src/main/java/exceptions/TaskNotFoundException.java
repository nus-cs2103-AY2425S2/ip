package exceptions;

/**
 * Concrete class that is thrown when a task matching a find command is not found
 */
public class TaskNotFoundException extends ThoughtBotException {
    /**
     * Constructor for TaskNotFoundException class
     */
    public TaskNotFoundException() {
        super("There are no tasks with the matching description.");
    }
}

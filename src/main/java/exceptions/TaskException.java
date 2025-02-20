package exceptions;

/**
 * This class handles the exceptions thrown by operations such as find, delete, etc.
 * in the {@link tasks.TaskManager TaskManager} Class.
 *
 * @author Yashvan
 */
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}

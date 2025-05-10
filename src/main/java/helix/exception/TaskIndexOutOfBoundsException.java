package helix.exception;

/**
 * Signals that a task index is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends HelixException {

    /**
     * Constructs a TaskIndexOutOfBoundsException with a detailed error message.
     *
     * @param index the index that was invalid
     * @param size  the size of the helix.task list
     */
    public TaskIndexOutOfBoundsException(int index, int size) {
        super("Task number " + index + " does not exist. You only have " + size + " helix.task(s) in your list.");
    }
}

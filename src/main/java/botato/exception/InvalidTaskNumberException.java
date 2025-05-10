package botato.exception;

/**
 * This exception is thrown when a task number is invalid (such as out of bounds) when a task number is expected.
 */
public class InvalidTaskNumberException extends BotatoException {
    /**
     * Creates a custom error message that shows a different string when the task list is currently empty, and when
     * the task list has tasks.
     * @param size is used to show a more interactive error message to the user.
     */
    public InvalidTaskNumberException(int size) {
        super("That task number didn't look quite right... "
                + (size == 0 ? "You have no tasks." : "You only have " + size + " task(s)!"));
    }
}

package eve.exception;

/**
 * Represents an exception caused by an invalid task number.
 * The task number is based on the order in the taskList.
 * It contains a custom error message to be displayed on the user interface.
 */
public class InvalidTaskNumException extends EveException {
    public InvalidTaskNumException() {
        super("The task number you specified does not exist...");
    }
}

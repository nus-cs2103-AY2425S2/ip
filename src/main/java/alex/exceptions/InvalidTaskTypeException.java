package alex.exceptions;

/**
 * Exception when the task information from storage is invalid
 */
public class InvalidTaskTypeException extends CorruptDataException {
    public InvalidTaskTypeException() {
        super("The task type does not exists");
    }
}

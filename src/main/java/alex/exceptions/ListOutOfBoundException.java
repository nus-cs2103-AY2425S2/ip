package alex.exceptions;

/**
 * Exception when the operation goes out of bound for task list
 */
public class ListOutOfBoundException extends AlexException {
    public ListOutOfBoundException() {
        super("Did you key in the correct index?");
    }
}

package exception;

/**
 * Represents an exception thrown when a command description is empty.
 */
public class EmptyDescriptionException extends BaimiException {
    public EmptyDescriptionException(String commandType) {
        super("The description of a " + commandType + " cannot be empty. Please provide a valid description.");
    }
}

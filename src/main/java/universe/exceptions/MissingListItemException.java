package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a check, uncheck or remove command,
 * but the index number provided exceeds the number of elements in the checklist.
 *
 * @author yuqing-tham
 */
public class MissingListItemException extends UniverseException {
    /**
     * Constructs a new MissingListItemException with the specified detail message.
     */
    public MissingListItemException() {
        super("This chore does not exist :(");
    }
}

package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a filter, find, remove command,
 * but without a number representing the index for the chore in the checklist.
 *
 * @author yuqing-tham
 */
public class MissingIndexException extends UniverseException {
    /**
     * Constructs a new MissingIndexException with the specified detail message.
     */
    public MissingIndexException() {
        super("Please provide a chore number!");
    }
}

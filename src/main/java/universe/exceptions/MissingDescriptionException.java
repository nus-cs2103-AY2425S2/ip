package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a ToDo, Deadline or Event command,
 * but without the Chore description.
 *
 * @author yuqing-tham
 */
public class MissingDescriptionException extends UniverseException {
    /**
     * Constructs a new MissingDescriptionException with the specified detail message.
     */
    public MissingDescriptionException() {
        super("A chore must have a description!");
    }
}

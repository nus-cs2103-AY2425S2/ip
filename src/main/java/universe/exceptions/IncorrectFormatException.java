package universe.exceptions;

/**
 * Represents an exception that is thrown when the data in the existing checklist file is incorrect.
 *
 * @author yuqing-tham
 */
public class IncorrectFormatException extends UniverseException {
    /**
     * Constructs a new IncorrectFormatException with the specified detail message.
     */
    public IncorrectFormatException() {
        super("Error in reading chore from the provided checklist.\n");
    }
}

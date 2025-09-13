package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a command,
 * with either formatting errors or missing details.
 *
 * @author yuqing-tham
 */
public class UniverseException extends Exception {
    /**
     * Constructs a new UniverseException class with the corresponding error message.
     */
    public UniverseException(String message) {
        super(message);
    }
}

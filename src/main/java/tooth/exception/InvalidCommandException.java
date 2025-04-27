package tooth.exception;

/**
 * Exception when command is not found
 */
public class InvalidCommandException extends ToothException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

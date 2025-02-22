package marshmallow;

/**
 * Represents a user-facing exception in Marshmallow.
 */
public class MarshmallowException extends RuntimeException {
    /**
     * Constructs a MarshmallowException with the specified message.
     *
     * @param message Message to be displayed to the user.
     */
    public MarshmallowException(String message) {
        super(message);
    }
}

package exception;

/**
 * Represents an exception specific to the Samantha task management system.
 */
public class SamanthaException extends Exception {

    /**
     * Constructs a new SamanthaException with a specific message.
     *
     * @param message The error message.
     */
    public SamanthaException(String message) {
        super(message);
    }
}

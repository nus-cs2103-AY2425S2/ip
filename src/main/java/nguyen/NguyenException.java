package nguyen;

/**
 * Custom exception class for handling errors in the Nguyen chatbot.
 */
public class NguyenException extends Exception {

    /**
     * Constructs a new NguyenException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public NguyenException(String message) {
        super(message);
    }
}

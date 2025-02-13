package zoe;

/**
 * Represents a custom exception for the Zoe application.
 * <p>
 * This exception is used to signal application-specific errors. It extends the standard
 * {@link Exception} class to provide a specific context for errors that occur within the Zoe
 * application.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     if (errorCondition) {
 *         throw new ZoeException("An error occurred while processing your request.");
 *     }
 * </pre>
 * </p>
 */
public class ZoeException extends Exception {
    /**
     * Constructs a new {@code ZoeException} with the specified detail message.
     *
     * @param message the detail message that explains the reason for the exception
     */
    public ZoeException(String message) {
        super(message);
    }
}

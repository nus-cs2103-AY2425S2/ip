package zoe;

/**
 * Represents an input/output exception specific to the Zoe application.
 * <p>
 * This exception is thrown when there is an error related to file or I/O operations within the
 * application. It extends {@link ZoeException} to provide a more specific context for I/O related errors.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     try {
 *         // code that may cause an I/O error
 *     } catch (IOException e) {
 *         throw new ZoeIoException("Failed to read the configuration file.");
 *     }
 * </pre>
 * </p>
 *
 * @see ZoeException
 */
public class ZoeIoException extends ZoeException {
    /**
     * Constructs a new {@code ZoeIoException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the I/O error
     */
    public ZoeIoException(String message) {
        super(message);
    }
}

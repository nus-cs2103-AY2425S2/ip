package ujin;

/**
 * Custom exception class for handling errors specific to the Ujin task management application.
 * This exception is thrown when an error occurs that is specific to the application's logic,
 * such as invalid commands or invalid data.
 */
public class UjinException extends Exception {

    /**
     * Default constructor for the `UjinException`.
     * Initializes a new exception without a message.
     */
    public UjinException() {}

    /**
     * Constructor for the `UjinException` that accepts a custom error message.
     * This allows for more detailed exception handling by providing context for the error.
     *
     * @param message The error message explaining the reason for the exception.
     */
    public UjinException(String message) {
        super(message);
    }
}

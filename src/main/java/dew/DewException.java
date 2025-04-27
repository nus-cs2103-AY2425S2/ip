package dew;

/**
 * The DewException class represents a custom exception used in the Dew application.
 * It provides formatted error messages for better readability.
 */
public class DewException extends Exception {
    /**
     * Constructs a DewException with a specified error message.
     *
     * @param message The error message to be displayed.
     */
    public DewException(String message) {
        super(message + System.lineSeparator());
    }
}

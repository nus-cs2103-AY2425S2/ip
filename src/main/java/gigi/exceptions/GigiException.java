package gigi.exceptions;

/**
 * Represents a custom exception specific to the Gigi application.
 * This exception is thrown when an error occurs within the application.
 */
public class GigiException extends Exception {

    /**
     * Constructs a GigiException with the specified error message.
     *
     * @param message The detail message describing the exception.
     */
    public GigiException(String message) {
        super(message);
    }
}

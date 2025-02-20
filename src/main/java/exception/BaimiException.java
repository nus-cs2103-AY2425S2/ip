package exception;

/**
 * Represents an exception specific to the Baimi application.
 * <p>
 * This exception is thrown when an error occurs during the execution of the Baimi application.
 */
public class BaimiException extends Exception {
    public BaimiException(String message) {
        super(message);
    }
}
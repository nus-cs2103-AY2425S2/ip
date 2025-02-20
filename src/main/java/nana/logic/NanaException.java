package nana.logic;

/**
 * Represents an exception specific to the Nana application.
 * This exception is thrown when there is an error related to Nana's operations.
 */
public class NanaException extends Exception {
    /**
     * Constructs a new NanaException with the specified detail message.
     *
     * @param message the detail message
     */
    public NanaException(String message) {
        super(message);
    }

}

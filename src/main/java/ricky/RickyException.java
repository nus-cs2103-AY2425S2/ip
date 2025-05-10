package ricky;

/**
 * Represents an exception specific to the Ricky application.
 */
public class RickyException extends Exception {

    /**
     * Constructs a RickyException with a default error message.
     */
    public RickyException() {
        super("Please enter a valid command.");
    }

    /**
     * Constructs a RickyException with the specified error message.
     *
     * @param message The error message.
     */
    public RickyException(String message) {
        super(message);
    }
}

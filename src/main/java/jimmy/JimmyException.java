package jimmy;

/**
 * The {@code JimmyException} class represents custom exceptions specific to the Jimmy application.
 * It extends the {@code Exception} class to handle application-specific error messages.
 */
public class JimmyException extends Exception {

    /**
     * Constructs a new {@code JimmyException} with the specified error message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public JimmyException(String message) {
        super(message);
    }
}

package botato.exception;

/**
 * The BotatoException class represents a custom exception in the bot application.
 * It extends RuntimeException and is used to handle specific errors that may occur
 * during the execution of bot commands or operations.
 */
public class BotatoException extends RuntimeException {

    /**
     * Constructs a new BotatoException with the specified error message.
     *
     * @param message The detail message describing the error.
     */
    public BotatoException(String message) {
        super(message);
    }
}

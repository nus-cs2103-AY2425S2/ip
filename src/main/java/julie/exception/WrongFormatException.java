package julie.exception;

/**
 * Exception thrown when a command input does not follow the expected syntax.
 * This helps in enforcing proper command formatting by the user using the chatbot.
 */
public class WrongFormatException extends Exception {

    /**
     * Constructs a new {@code WrongFormatException} with the specified detail message.
     *
     * @param message The detail message explaining the format error.
     */
    public WrongFormatException(String message) {
        super(message);
    }
}

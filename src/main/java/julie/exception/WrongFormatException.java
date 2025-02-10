package julie.exception;

/**
 * Represents an exception that is thrown when a user inputs a command in an incorrect format.
 * This exception is used to enforce proper command syntax in the chatbot.
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


package qwerty.exceptions;

/**
 * Represents an exception that is thrown when an unrecognized command is entered.
 * This exception extends {@link BotException} and provides a custom error message
 * indicating that the command is not understood.
 */
public class UnknownCommandException extends BotException {

    /**
     * Returns a string representation of the exception.
     *
     * @return A custom error message indicating that the command is not recognized.
     */
    @Override
    public String toString() {
        // Custom error message
        return super.toString() + " I don't understand the words that are coming out of your mouth!";
    }
}

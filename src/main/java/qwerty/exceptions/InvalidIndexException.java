package qwerty.exceptions;

/**
 * Represents an exception that is thrown when an invalid index is provided.
 * This exception extends {@link BotException} and provides a custom error message
 * indicating that the index is not valid.
 */
public class InvalidIndexException extends BotException {

    /**
     * Returns a string representation of the exception.
     *
     * @return A custom error message indicating that the index provided is invalid.
     */
    @Override
    public String toString() {
        return super.toString() + " Please make sure you enter a valid index!"; // Custom error message.
    }
}

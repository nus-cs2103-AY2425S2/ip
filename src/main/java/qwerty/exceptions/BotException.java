package qwerty.exceptions;

/**
 * Represents a custom exception for handling errors in the bot's operation.
 * This exception can be thrown when something unexpected happens during the bot's execution.
 */
public class BotException extends Exception {

    /**
     * Returns a string representation of the exception.
     *
     * @return A custom error message "Huh?!" to indicate an unexpected error.
     */
    @Override
    public String toString() {
        return "Huh?!";
    }
}

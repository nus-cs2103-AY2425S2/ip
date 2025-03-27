package qwerty.exceptions;

/**
 * Represents an exception that is thrown when a task description is empty.
 * This exception extends {@link BotException} and provides a custom error message.
 */
public class EmptyTaskNameException extends BotException {

    /**
     * Returns a string representation of the exception.
     *
     * @return A custom error message indicating that the task description cannot be empty.
     */
    @Override
    public String toString() {
        return super.toString() + " The description of a task cannot be empty!"; // Custom error message.
    }
}

package qwerty.exceptions;

/**
 * Represents an exception that is thrown when the format of a command is incorrect.
 * This exception extends {@link BotException} and provides a custom error message
 * related to the specific command type.
 */
public class IncorrectFormatException extends BotException {

    private final String COMMAND_TYPE; // Type of the command with incorrect format.

    /**
     * Constructs an IncorrectFormatException with the specified command type.
     *
     * @param commandType The type of the command (e.g., "todo", "deadline") that has the incorrect format.
     */
    public IncorrectFormatException(String commandType) {
        super();
        this.COMMAND_TYPE = commandType;
    }

    /**
     * Returns a string representation of the exception.
     *
     * @return A custom error message indicating that the specified command type
     *         has an incorrect description format.
     */
    @Override
    public String toString() {
        return super.toString() + " Please make sure your " + COMMAND_TYPE + " description is formatted correctly!";
    }
}

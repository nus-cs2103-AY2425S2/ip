package exception;

/**
 * Represents an exception specific to the Command.
 */
public class CommandException extends SamanthaException {

    /**
     * Constructs a new CommandException with a specific message.
     *
     * @param message The error message.
     */
    public CommandException(String message) {
        super(message);
    }
}

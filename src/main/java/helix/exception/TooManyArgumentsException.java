package helix.exception;

/**
 * Signals that a command has received more arguments than expected.
 */
public class TooManyArgumentsException extends HelixException {

    /**
     * Constructs a {@code TooManyArgumentsException} with a default error message.
     */
    public TooManyArgumentsException() {
        super("Too many arguments provided. Please check the command format.");
    }

    /**
     * Constructs a {@code TooManyArgumentsException} with a custom error message.
     *
     * @param command the command name
     * @param exampleFormat an example of the correct command format
     */
    public TooManyArgumentsException(String command, String exampleFormat) {
        super("The command '" + command + "' received too many arguments. "
                + "Example: " + exampleFormat);
    }
}

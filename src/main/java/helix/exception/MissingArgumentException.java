package helix.exception;

/**
 * Signals that a required argument is missing for a command.
 */
public class MissingArgumentException extends HelixException {

    /**
     * Constructs a {@code MissingArgumentException} with the specific command
     * name and an example format to guide the user.
     *
     * @param command       the name of the command missing required arguments (e.g., "todo", "deadline")
     * @param exampleFormat an example format showing the correct syntax for the command
     */
    public MissingArgumentException(String command, String exampleFormat) {
        super("The command '" + command + "' requires additional arguments. "
                + "Example: " + exampleFormat);
    }
}

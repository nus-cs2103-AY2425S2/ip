package adam.exceptions;

/**
 * Represents an exception where an argument is missing.
 */
public class MissingArgument extends AdamException {
    public MissingArgument(String argumentName) {
        super("Argument " + argumentName + " is missing!");
    }
}

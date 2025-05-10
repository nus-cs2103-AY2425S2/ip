package laffy.command.exceptions;

/**
 * Represents an exception when there are too many arguments.
 */
public class TooManyArguments extends LaffyException {
    private static final String message = "Too many arguments. ";

    public TooManyArguments(String explanation) {
        super(message + explanation);
    }
}

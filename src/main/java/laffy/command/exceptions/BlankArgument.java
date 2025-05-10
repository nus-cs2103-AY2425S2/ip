package laffy.command.exceptions;

/**
 * Represents an exception when there is a missing argument.
 */
public class BlankArgument extends LaffyException {
    private static final String message = "Missing argument. ";

    public BlankArgument(String explanation) {
        super(message + explanation);
    }
}

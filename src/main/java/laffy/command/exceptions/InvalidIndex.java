package laffy.command.exceptions;

/**
 * Represents an exception when the index is invalid.
 */
public class InvalidIndex extends LaffyException {
    private static final String message = "Invalid index. ";

    public InvalidIndex(String explanation) {
        super(message + explanation);
    }
}

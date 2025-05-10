package laffy.command.exceptions;

/**
 * Represents an exception when there is a missing keyword flag.
 */
public class MissingKeywordFlag extends LaffyException {
    private static final String message = "Missing keyword flag. ";

    public MissingKeywordFlag(String explanation) {
        super(message + explanation);
    }
}

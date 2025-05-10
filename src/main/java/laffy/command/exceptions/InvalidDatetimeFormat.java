package laffy.command.exceptions;

/**
 * Represents an exception when the datetime format is invalid.
 */
public class InvalidDatetimeFormat extends LaffyException {
    private static final String message = "Invalid datetime format.\nExpected: dd-MM-yy [HHmm].\nInstead got: ";

    public InvalidDatetimeFormat(String explanation) {
        super(message + explanation);
    }
}

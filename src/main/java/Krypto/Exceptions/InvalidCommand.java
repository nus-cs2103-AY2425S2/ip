package Krypto.Exceptions;

/**
 * Represents an exception thrown when an invalid command is entered by the user.
 */
public class InvalidCommand extends KryptoExceptions {
    private final String unknown;

    /**
     * Constructs an InvalidCommand exception with the specified unknown command.
     *
     * @param unknown The unknown command that caused the exception.
     */
    public InvalidCommand(String unknown) {
        super("");
        this.unknown = unknown;
    }

    @Override
    public String toString() {
        return String.format("Sorry. I don't know what `%s` means.",
                this.unknown);
    }
}
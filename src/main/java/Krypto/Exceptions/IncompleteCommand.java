package Krypto.Exceptions;

/**
 * Represents an exception thrown when a command has either too many or too few arguments.
 */
public class IncompleteCommand extends KryptoExceptions {
    private final String type;
    private final String format;

    /**
     * Constructs an IncompleteCommand exception with the specified command type.
     *
     * @param type The type of command that has an incomplete number of arguments.
     */
    public IncompleteCommand(String type, String format) {
        super("");
        this.type = type;
        this.format = format;
    }


    @Override
    public String toString() {
        return String.format("Invalid format for the instruction %s\n Use format %s", type, format);
    }
}
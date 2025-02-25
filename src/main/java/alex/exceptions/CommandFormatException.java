package alex.exceptions;

/**
 * The exception when the command is not in correct format and cannot be parsed correctly
 */
public class CommandFormatException extends AlexException {
    public CommandFormatException() {
        super("There is something wrong with the command format");
    }
}

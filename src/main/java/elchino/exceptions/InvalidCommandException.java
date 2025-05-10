package elchino.exceptions;

/**
 * Exception for handling invalid commands.
 */
public class InvalidCommandException extends ElchinoException {
    /**
     * Constructor for InvalidCommandException.
     * @param command The invalid command entered by the user.
     */
    public InvalidCommandException(String command) {
        super("Comando inválido: " + command);
    }
}
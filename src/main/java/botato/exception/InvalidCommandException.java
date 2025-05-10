package botato.exception;

/**
 * This exception is thrown when a command is not valid or not formatted correctly.
 */
public class InvalidCommandException extends BotatoException {
    /**
     * Adds a custom error message to print out.
     */
    public InvalidCommandException() {
        super("I don't understand that, I'm just a potato...\nSay 'help' to see what I learnt to do so far!");
    }
}

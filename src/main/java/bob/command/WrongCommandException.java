package bob.command;

/**
 * This class represents an exception that is thrown when the user
 * enters an invalid command.
 */
public class WrongCommandException extends Exception {
    public WrongCommandException(String message) {
        super(message);
    }
}

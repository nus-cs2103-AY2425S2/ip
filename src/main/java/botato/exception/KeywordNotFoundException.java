package botato.exception;

/**
 * This exception is thrown when a find command does not find a specified keyword in the task list.
 */
public class KeywordNotFoundException extends BotatoException {
    public KeywordNotFoundException() {
        super("Sorry, I didn't find any tasks matching your keyword...");
    }
}

package exceptions;

/**
 * This class handles the exceptions thrown when creating instances of the
 * in the {@link tasks.Todo Todo}, {@link tasks.Deadline Deadline}
 * and {@link tasks.Event Event} classes.
 *
 * @author Yashvan
 */
public class MarkException extends Exception {
    public MarkException(String message) {
        super(message);
    }
}

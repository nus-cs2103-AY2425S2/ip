package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a deadline command,
 * but without a deadline date/time.
 *
 * @author yuqing-tham
 */
public class MissingTimeException extends UniverseException {
    /**
     * Constructs a new MissingTimeException with the specified detail message.
     */
    public MissingTimeException() {
        super("A deadline chore must have a date/time!");
    }
}

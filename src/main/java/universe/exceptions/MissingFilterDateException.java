package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a filter command,
 * but without the date to filter.
 *
 * @author yuqing-tham
 */
public class MissingFilterDateException extends UniverseException {
    /**
     * Constructs a new MissingFilterDateException with the specified detail message.
     */
    public MissingFilterDateException() {
        super("Please provide a date to filter by.");
    }
}

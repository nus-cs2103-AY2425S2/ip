package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs a find command,
 * but without a keyword to search.
 *
 * @author yuqing-tham
 */
public class MissingKeywordException extends UniverseException {
    /**
     * Constructs a new MissingKeywordException with the specified detail message.
     */
    public MissingKeywordException() {
        super("Please provide a keyword.");
    }
}

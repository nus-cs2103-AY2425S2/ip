package partillay.exception;

/**
 * Represents the exception where a command given is invalid.
 */
public class PartillayInvalidCommandException extends PartillayException {
    public PartillayInvalidCommandException(String message) {
        super(message);
    }
}

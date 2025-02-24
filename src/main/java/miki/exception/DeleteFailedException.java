package miki.exception;

/**
 * Represents an exception that occurs when a delete fails.
 */
public class DeleteFailedException extends MikiException {
    public DeleteFailedException() {
        super();
    }

    public DeleteFailedException(String message) {
        super("Delete Task failed\n" + message);
    }
}

package miki.exception;

/**
 * Represents an exception that occurs when a check fails.
 */
public class CheckException extends MikiException {
    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }
}

package miki.exception;

/**
 * Represents an exception that occurs when a command is not found.
 */
public class NoCommandException extends MikiException {
    public NoCommandException() {
    }

    public NoCommandException(String message) {
        super(message);
    }
}

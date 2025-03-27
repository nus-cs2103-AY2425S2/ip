package yapper.exceptions;

/**
 * Exception thrown when a required argument for a task is missing.
 */
public class MissingTaskArgs extends RuntimeException {

    /**
     * Constructs a new {@code MissingTaskArgs} exception with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public MissingTaskArgs(String message) {
        super(message);
    }
}

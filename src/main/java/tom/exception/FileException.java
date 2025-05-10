package tom.exception;

/**
 * Exception thrown when an error during file saving/loading is encountered.
 */
public class FileException extends TomCommandException {

    /**
     * Constructs an FileException with the specified detail message.
     *
     * @param message The detail message.
     */
    public FileException(String message) {
        super(message);
    }
}

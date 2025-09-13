package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs an empty response.
 *
 * @author yuqing-tham
 */
public class EmptyResponseException extends UniverseException {
    /**
     * Constructs a new EmptyResponseException with the specified detail message.
     */
    public EmptyResponseException() {
        super("Still waiting for your response!");
    }
}

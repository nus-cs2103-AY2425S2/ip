package runny;
/**
 * Exception class for Runny Chatbot errors.
 */
public class RunnyException extends Exception {
    /**
     * Creates a RunnyException with an error message.
     *
     * @param errorMessage The error message.
     */
    public RunnyException(String errorMessage) {
        super(errorMessage);
    }
}

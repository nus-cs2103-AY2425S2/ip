package tooth.exception;

/**
 * ToothException
 */
public class ToothException extends RuntimeException {
    public ToothException(String message) {
        super("Tooth found an error: " + message);
    }
}

package tooth.exception;

/**
 * Exception when there is not enough param or the param is invalid
 */
public class InvalidParamException extends ToothException {
    public InvalidParamException(String message) {
        super(message);
    }
}

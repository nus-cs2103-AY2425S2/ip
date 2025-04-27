package tooth.exception;

/**
 * Execption when file is in wrong format
 */
public class InvalidFileFormatException extends ToothException {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}

package exception;

/**
 * SunpterException targeted at IllegalArgumentExceptions
 */
public class SunpterException extends IllegalArgumentException {
    public SunpterException(String message) {
        super(message);
    }
}


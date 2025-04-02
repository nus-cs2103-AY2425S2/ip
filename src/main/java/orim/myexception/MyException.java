package orim.myexception;

/**
 * Represents special exception when running Orim.
 */
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}

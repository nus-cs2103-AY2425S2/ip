package alex.exceptions;

public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException() {
        super("Make sure you key in time in yyyy-mm-dd format!");
    }
}

package woof.exception;

public class IllegalDateTimeException extends Exception {
    /**
     * Wrong date and time format instead of "yyyy-MM-dd hh:mm".
     * @param message
     */
    public IllegalDateTimeException(String message) {
        super("WERWER! Please enter a valid date with the format yyyy-mm-dd hh:mm");
    }
}

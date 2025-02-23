package woof.exception;

public class IllegalDateException extends Exception {
    /**
     * Wrong date format instead of "yyyy-MM-dd".
     * @param message
     */
    public IllegalDateException(String message) {
        super("WERWER! Please enter a valid date with the format yyyy-mm-dd");
    }
}

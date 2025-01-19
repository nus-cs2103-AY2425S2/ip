public class EmptyException extends IndexOutOfBoundsException {
    /**
     * Constructs an Empty Exception if the description is empty.
     *
     * @param message is the message of the error.
     */
    public EmptyException(String message) {
        super(message);
    }
}

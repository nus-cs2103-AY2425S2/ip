package buddy.exception;

/**
 * Represents the type Buddy invalid date format exception.
 */
public class BuddyInvalidDateFormatException extends BuddyException {
    /**
     * Instantiates a new Buddy invalid date format exception.
     *
     * @param message the message
     */
    public BuddyInvalidDateFormatException(String message) {
        super(message);
    }


    @Override
    public String toString() {
        return super.toString() + message;
    }
}

package buddy.exception;

/**
 * Represents the type Buddy invalid command arguments exception.
 */
public class BuddyInvalidCommandArgumentsException extends BuddyException {

    /**
     * Instantiates a new Buddy invalid command arguments exception.
     *
     * @param message the message
     */
    public BuddyInvalidCommandArgumentsException(String message) {
        super(message);
    }

    /**
     * Retrieves information string of the exception.
     *
     * @return information String of the exception.
     */
    @Override
    public String toString() {
        return super.toString() + this.message;
    }
}

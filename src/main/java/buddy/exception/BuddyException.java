package buddy.exception;

/**
 * Represents the type Buddy exception.
 */
public class BuddyException extends Exception {
    protected String message;

    public BuddyException(String message) {
        this.message = message;
    }

    public BuddyException() {
    }

    /**
     * Retrieves information string of the exception.
     *
     * @return information string of the exception.
     */
    @Override
    public String toString() {
        return "Attention !! ";
    }
}

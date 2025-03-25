package buddy.exception;

/**
 * Represents the type Buddy data storage exception.
 */
public class BuddyDataStorageException extends BuddyException {

    /**
     * Constructor for BuddyDataStorageException.
     *
     * @param message message string for exception.
     */
    public BuddyDataStorageException(String message) {
        super(message);
    }

    /**
     * Retrieves information string of the exception.
     *
     * @return information string of the exception.
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.message;
    }
}

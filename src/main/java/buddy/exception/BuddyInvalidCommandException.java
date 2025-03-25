package buddy.exception;

/**
 * Represents the type Buddy invalid command exception.
 */
public class BuddyInvalidCommandException extends BuddyException {
    private final String command;

    /**
     * Constructor for BuddyInvalidCommandException.
     *
     * @param userCommand Invalid command from the user.
     */
    public BuddyInvalidCommandException(String userCommand) {
        this.command = userCommand;
    }

    /**
     * Retrieves information string of the exception.
     *
     * @return information string of the exception.
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" Your command \"%s\" seems strange to me", this.command);
    }
}

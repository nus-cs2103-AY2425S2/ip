package buddy.exception;

import buddy.command.CommandType;

/**
 * Represents the type Buddy missing command info exception.
 */
public class BuddyMissingCommandInfoException extends BuddyException {
    private final String command;

    /**
     * Constructor for BuddyMissingCommandInfoException.
     *
     * @param command The command that don't have description.
     */
    public BuddyMissingCommandInfoException(String command) {
        this.command = command;
    }

    /**
     * Retrieves information string of the exception.
     *
     * @return information string of the exception.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Make sure your %s command follows this format:\n %s",
                this.command, CommandType.valueOf(command.toUpperCase()));
    }
}

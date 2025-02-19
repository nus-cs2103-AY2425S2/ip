package usercommands;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the MARK and UNMARK command, including
 * the index of the task to be marked or unmarked
 */
public class UserCommandMarkUnmark extends UserCommand {
    private int markUnmarkNumber;

    /**
     * Constructor for the UserCommandMarkUnmark class
     * @param commandType Mark or Unmark
     * @param markUnmarkNumber Index of the task to be marked or unmarked
     */
    public UserCommandMarkUnmark(Command commandType, int markUnmarkNumber) {
        super(commandType);
        this.markUnmarkNumber = markUnmarkNumber;
    }

    public int getMarkUnmarkNumber() {
        return markUnmarkNumber;
    }
}

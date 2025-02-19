package usercommands;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the DELETE command, namely
 * the index of the task to be deleted
 */
public class UserCommandDelete extends UserCommand {
    private int deleteNumber;

    /**
     * Constructor for the UserCommandDelete class
     * @param deleteNumber Index of the task to be deleted
     */
    public UserCommandDelete(int deleteNumber) {
        super(Command.DELETE);
        this.deleteNumber = deleteNumber;
    }

    public int getDeleteNumber() {
        return deleteNumber;
    }
}

package usercommands;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the find command, being
 * the word to be matched
 */
public class UserCommandFind extends UserCommand {
    private String findString;

    /**
     * Constructor for the UserCommandFind class
     * @param findString String to be matched
     */
    public UserCommandFind(String findString) {
        super(Command.FIND);
        this.findString = findString;
    }

    public String getFindString() {
        return findString;
    }
}

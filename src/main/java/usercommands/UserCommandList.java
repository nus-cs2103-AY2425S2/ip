package usercommands;

import utilities.Command;

/**
 * Concrete class that encapsulates information about the LIST command
 */
public class UserCommandList extends UserCommand {
    public UserCommandList() {
        super(Command.LIST);
    }
}

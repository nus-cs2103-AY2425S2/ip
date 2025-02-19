package usercommands;

import utilities.Command;

/**
 * Abstract parent class of all UserCommand classes, used to encapsulate information about
 * the user input. Every UserCommand object will have a commandType variable that encapsulates
 * the command the user is trying to execute.
 */
public abstract class UserCommand {
    private Command commandType;

    public UserCommand(Command commandType) {
        this.commandType = commandType;
    }

    public Command getCommandType() {
        return this.commandType;
    }
}

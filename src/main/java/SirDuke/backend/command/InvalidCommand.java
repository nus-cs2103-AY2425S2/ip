package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;

/**
 * The InvalidCommand class represents an invalid command.
 * It does nothing other than to inform the user that the command is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Create an Incomplete command.
     */
    public InvalidCommand() {
        super("");
    }

    /**
     * InvalidCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        return UI.informThatCommandIsInvalid();
    }
}

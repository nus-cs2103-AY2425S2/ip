package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.ToDoList;
import SirDuke.backend.Storage;

/**
 * The IncompleteCommand class represents an incomplete command.
 * It does nothing other than to inform the user that the command is incomplete.
 */
public class IncompleteCommand extends Command {

    /**
     * Create an Incomplete command.
     *
     */
    public IncompleteCommand() {
        super("");
    }

    /**
     * IncompleteCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        return UI.informThatCommandIsIncomplete();
    }
}

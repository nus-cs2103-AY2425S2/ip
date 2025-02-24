package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.ToDoList;
import SirDuke.backend.Storage;

public class IncompleteCommand extends Command {

    public IncompleteCommand() {
        super("");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        return UI.informThatCommandIsIncomplete();
    }
}

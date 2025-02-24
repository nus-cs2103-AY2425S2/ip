package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super("");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        return UI.informThatCommandIsInvalid();
    }
}

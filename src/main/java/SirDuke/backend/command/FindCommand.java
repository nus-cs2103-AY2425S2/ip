package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    /**
     * ListCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        String temp = toDoList.findTask(input);
        if (temp != "[]") {
            return temp;
        } else {
            return UI.informThatTaskIsNotFound();
        }
    }
}

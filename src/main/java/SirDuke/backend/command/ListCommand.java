package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
public class ListCommand extends Command {


    public ListCommand() {
        super("");
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
        return toDoList.showList();
    }
}

package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
public class ListCommand extends Command {

    /**
     * Create a List command.
     */
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

    /**
     * Execute the List command.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return The list of tasks in the ToDoList as a String.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        return toDoList.showList();
    }
}

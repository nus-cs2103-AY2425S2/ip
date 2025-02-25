package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;

/**
 * The FindCommand class represents a command to find a task in the ToDoList given a certain keyword.
 */
public class FindCommand extends Command {

    /**
     * Create a Find command.
     *
     * @param input The keyword to search for in the tasks in the ToDoList.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * FindCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Find the tasks in the ToDoList that contain the keyword.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return The list of tasks in the ToDoList that contain the keyword as a String.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        String temp = toDoList.findTask(input);
        if (temp != "[]") { //no tasks matching the keyword can been found
            return temp;
        } else {
            return UI.informThatTaskIsNotFound();
        }
    }
}

package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
/**
 * The MarkCommand class represents a command to mark a task in toDolist as undone.
 */
public class MarkCommand extends Command {

    /**
     * Create a Mark command.
     * @param input The un-parsed input from the user,
     *              which will be parsed in the <c>execute()</c> method as an index.
     *
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Mark command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks a task as done.
     * @param toDoList toDoList to mark task as done in
     * @return String representing the MarkCommand's execution status
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            int index = Integer.parseInt(input) - 1;
            toDoList.markTaskAsDone(index);
            return UI.markTaskAsDone(toDoList.getTask(index));
        } catch (NumberFormatException e) { //input is not an integer
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) { //task index is out of bounds, i.e. task does not exist
            return UI.informThatTaskDoesNotExist();
        }
    }
}


package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;


/**
 * The DeleteCommand class represents a command to delete a task in the ToDoList.
 */
public class DeleteCommand extends Command {

    /**
     * Create a Delete command.
     *
     * @param input The un-parsed input from the user,
     *              which will be parsed in the <c>execute()</c> method as an index.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * DeleteCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Delete a task in the ToDoList.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return String representing DeleteCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            toDoList.deleteTask(Integer.parseInt(input) - 1);
            return UI.informThatTaskDeleteIsSuccessful();
        } catch (NumberFormatException e) { //input is not an integer
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) { //task index is out of bounds, i.e. task does not exist
            return UI.informThatTaskDoesNotExist();
        }
    }
}

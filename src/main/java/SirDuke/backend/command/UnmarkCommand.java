package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;

/**
 * The UnmarkCommand class represents a command to mark a task in tasklist as undone.
 */
public class UnmarkCommand extends Command {

    /**
     * Create an Unmark command.
     * @param input The un-parsed input from the user,
     *               which will be parsed in the <c>execute()</c> method as an index.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Unmark command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Un-marks task as done.
     * @param toDoList toDoList to un-mark task as done in
     * @return String representing the UnmarkCommand's execution status
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            toDoList.unmarkTaskAsDone(Integer.parseInt(input) - 1);
            return UI.unmarkTaskAsDone();
        } catch (NumberFormatException e) { //input is not an integer
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) { //task index is out of bounds, i.e. task does not exist
            return UI.informThatTaskDoesNotExist();
        }
    }
}

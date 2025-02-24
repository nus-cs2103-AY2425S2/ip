package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
/**
 * The UnmarkCommand class represents a command to mark a task in tasklist as undone.
 */
public class UnmarkCommand extends Command {

    /**
     * Create an Unmark command.
     * @param input
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
     * Marks task as undone.
     * @param toDoList
     * @param storage
     * @return String representing Unmark command
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            toDoList.unmarkTaskAsDone(Integer.parseInt(input) - 1);
            return UI.unmarkTaskAsDone();
        } catch (IndexOutOfBoundsException e) { //task is not in toDoList
            return UI.informThatTaskDoesNotExist();
        } catch (NumberFormatException e) { //input is not an integer
            return UI.informThatTaskIndexIsInvalid();
        }
    }
}

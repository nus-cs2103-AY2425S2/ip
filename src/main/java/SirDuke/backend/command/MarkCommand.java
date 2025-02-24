package SirDuke.backend.command;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.UI;
/**
 * The UnmarkCommand class represents a command to mark a task in tasklist as undone.
 */
public class MarkCommand extends Command {

    /**
     * Create a Mark command.
     * @param input
     */
    public MarkCommand(String input) {
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
            int index = Integer.parseInt(input) - 1;
            toDoList.markTaskAsDone(index);
            return UI.markTaskAsDone(toDoList.getTask(index));
        } catch (IndexOutOfBoundsException e) { //task does not exist in toDoList
            return UI.informThatTaskDoesNotExist();
        } catch (NumberFormatException e) { //input is not an integer
            return UI.informThatTaskIndexIsInvalid();
        }
    }
}


package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.backend.task.Task;

/**
 * The EditDescriptionCommand class represents a command to edit description of a task in the ToDoList.
 */
public class EditDescriptionCommand extends Command {

    /**
     * Create an EditDescription command.
     *
     * @param input The index of the task to edit and the new description of the task.
     */
    public EditDescriptionCommand(String input) {
        super(input);
    }

    /**
     * EditDescriptionCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input to get the index of the task to edit and the new description of the task,
     * then gets the task from toDoList and edits its description.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return String representing DeadlineCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewDescription = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewDescription[0]) - 1;
            String newDescription = taskIndexAndNewDescription[1];
            Task task = toDoList.getTask(taskIndex);
            task.setDescription(newDescription);
            return UI.informThatTaskHasBeenEdited();
        } catch (ArrayIndexOutOfBoundsException e) { //incomplete command
            return UI.informThatCommandIsIncomplete();
        } catch (NumberFormatException e) { //task index is not an integer
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) { //task index is out of bounds, i.e. task does not exist
            return UI.informThatTaskDoesNotExist();
        }
    }
}
package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;
import seedu.SirDuke.backend.task.DeadlineTask;
import seedu.SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The EditToBeCompletedByCommand class represents a command to edit the time to be completed by
 * of a deadline task in the ToDoList.
 */
public class EditToBeCompletedByCommand extends Command {

    /**
     * Create an EditToBeCompletedBy command.
     *
     * @param input The index of the task to edit and the new end time of the event task.
     */
    public EditToBeCompletedByCommand(String input) {
        super(input);
    }

    /**
     * EditToBeCompletedByCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input to get the index of the task to edit and the new time for the task to be completed by,
     * then gets the task from toDoList and edits it's time to be completed by.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return String representing EditToBeCompletedByCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewTime = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewTime[0]) - 1;
            String newTime = taskIndexAndNewTime[1];
            Task task = toDoList.getTask(taskIndex);
            if (task instanceof DeadlineTask) {
                ((DeadlineTask) task).setToBeCompletedBy(newTime);
                return UI.informThatTaskHasBeenEdited();
            } else { //task is not a deadline task, so cannot edit time to be completed by
                return UI.informThatCommandDoesNotWorkOnTask(task);
            }
        } catch (ArrayIndexOutOfBoundsException e) { //incomplete command
            return UI.informThatCommandIsIncomplete();
        } catch (DateTimeParseException e) { //new time is not a valid date time
            return UI.informThatDateTimeIsInvalid();
        } catch (NumberFormatException e) { //task index is not an integer
            return UI.informThatTaskIndexIsInvalid();
        } catch (IndexOutOfBoundsException e) { //task index is out of bounds, i.e. task does not exist
            return UI.informThatTaskDoesNotExist();
        }
    }
}
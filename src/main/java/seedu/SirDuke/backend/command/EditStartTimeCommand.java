package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;
import seedu.SirDuke.backend.exception.IllegalStartAndEndTimeException;
import seedu.SirDuke.backend.task.EventTask;
import seedu.SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The EditStartTimeCommand class represents a command to edit the start time of an event task in the ToDoList.
 */
public class EditStartTimeCommand extends Command {

    /**
     * Create an EditStartTime command.
     *
     * @param input The index of the task to edit and the new start time of the event task.
     */
    public EditStartTimeCommand(String input) {
        super(input);
    }

    /**
     * EditStartTimeCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input to get the index of the task to edit and the new start time of the task,
     * then gets the task from toDoList and edits its start time.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return String representing EditStartTimeCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewTime = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewTime[0]) - 1;
            String newTime = taskIndexAndNewTime[1];
            Task task = toDoList.getTask(taskIndex);
            if (task instanceof EventTask) {
                ((EventTask) task).setStartTime(newTime);
                return UI.informThatTaskHasBeenEdited();
            } else { //task is not an event task, so cannot edit start time
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
        } catch (IllegalStartAndEndTimeException e) { //new start time is after end time
            return e.toString();
        }
    }
}

package SirDuke.backend.command;

import SirDuke.UI;
import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.backend.exception.IllegalStartAndEndTimeException;
import SirDuke.backend.task.EventTask;
import SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The EditEndTimeCommand class represents a command to edit the end time of an event task in the ToDoList.
 */
public class EditEndTimeCommand extends Command {

    /**
     * Create an EditEndTime command.
     *
     * @param input The index of the task to edit and the new end time of the event task.
     */
    public EditEndTimeCommand(String input) {
        super(input);
    }

    /**
     * EditEndTimeCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input to get the index of the task to edit and the new end time of the task,
     * then gets the task from toDoList and edits its endTime.
     * @param toDoList The ToDoList object that contains the list of tasks.
     * @return String representing EditEndTimeCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] taskIndexAndNewTime = input.split(" ", 2);
            int taskIndex = Integer.parseInt(taskIndexAndNewTime[0]) - 1;
            String newTime = taskIndexAndNewTime[1];
            Task task = toDoList.getTask(taskIndex);
            if (task instanceof EventTask) {
                ((EventTask) task).setEndTime(newTime);
                return UI.informThatTaskHasBeenEdited();
            } else { //task is not an event task, so cannot edit end time
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
        } catch (IllegalStartAndEndTimeException e) { //new end time is before start time
            return e.toString();
        }
    }
}
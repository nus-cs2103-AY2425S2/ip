package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;
import seedu.SirDuke.backend.exception.IllegalStartAndEndTimeException;
import seedu.SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The EventCommand class represents a command to create a DeadlineTask.
 */
public class EventCommand extends Command{

    /**
     * Create an Event command.
     *
     * @param input The un-parsed input from the user,
     *              which will be parsed in the <c>execute()</c> method as an index.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * EventCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input into the respective fields required for an EventTask,
     * then creates an EventTask and adds it to the toDoList
     * via <c>toDoList.createEventTask()</c>.
     * @return String representing EventCommand's execution status
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] eventName = input.split(" /start ");
            String[] startAndEnd = eventName[1].split(" /end ");
            toDoList.createEventTask(eventName[0], startAndEnd[0], startAndEnd[1]);
            Task eventTask = toDoList.getTask(toDoList.getLength() - 1);
            assert (eventTask != null) : "Task should not be null";
            return UI.informThatTaskHasBeenCreated(eventTask);
        } catch (ArrayIndexOutOfBoundsException e) { //command is incomplete
            return UI.informThatCommandIsIncomplete();
        } catch (DateTimeParseException e) { //date is not in the correct format
            return UI.informThatDateTimeIsInvalid();
        } catch (IllegalStartAndEndTimeException e) { //start time is after end time
            return e.toString();
        }
    }
}
package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;
import seedu.SirDuke.backend.task.Task;

import java.time.format.DateTimeParseException;

/**
 * The DeadlineCommand class represents a command to create a DeadlineTask.
 */
public class DeadlineCommand extends Command{

    /**
     * Create a Deadline command.
     *
     * @param input The un-parsed input from the user,
     *              which will be parsed in the <c>execute()</c> method.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * DeadlineCommand does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses input into the respective fields required for a DeadlineTask,
     * then creates a DeadlineTask and adds it to the toDoList
     * via <c>toDoList.createDeadlineTask()</c>.
     * @return String representing DeadlineCommand's execution status.
     */
    @Override
    public String execute(ToDoList toDoList, Storage storage) {
        try {
            String[] split = input.split(" /by ");
            toDoList.createDeadlineTask(split[0], split[1]);
            Task deadlineTask = toDoList.getTask(toDoList.getLength() - 1);
            assert (deadlineTask != null) : "Task should not be null";
            return UI.informThatTaskHasBeenCreated(deadlineTask);
        } catch (ArrayIndexOutOfBoundsException e) { //command is incomplete
                return UI.informThatCommandIsIncomplete();
        } catch (DateTimeParseException e) { //date is not in the correct format
            return UI.informThatDateTimeIsInvalid();
        }
    }
}

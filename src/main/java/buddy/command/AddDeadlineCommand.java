package buddy.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandArgumentsException;
import buddy.exception.BuddyInvalidDateFormatException;
import buddy.storage.DataStorage;
import buddy.task.Deadline;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represents the type Add deadline command.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Instantiates a new Add deadline command.
     *
     * @param args the arguments from the user command
     */
    public AddDeadlineCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        try {
            LocalDateTime by = getDateAndTime(args.get(1));
            Task task = new Deadline(args.get(0), by);
            taskList.addTask(task);
            dataStorage.saveTasksToStorage(taskList);
            return Display.addTask(task, taskList);
        } catch (IndexOutOfBoundsException error) {
            throw new BuddyInvalidCommandArgumentsException("Please enter deadline command in the"
                    + " following format \n deadline [description] /by [yyyy-MM-dd HHmm]");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new BuddyInvalidDateFormatException("Please enter the date format of deadline command as follows: \n"
                    + "yyyy-MM-dd HHmm (e.g 2000-02-02 1400)");
        }
    }
}

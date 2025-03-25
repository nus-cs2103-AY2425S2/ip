package buddy.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandArgumentsException;
import buddy.exception.BuddyInvalidDateFormatException;
import buddy.storage.DataStorage;
import buddy.task.Event;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represents The type Add event command.
 */
public class AddEventCommand extends Command {

    /**
     * Instantiates a new Add event command.
     *
     * @param args the args from the user command
     */
    public AddEventCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        try {
            LocalDateTime from = Command.getDateAndTime(args.get(1));
            LocalDateTime to = Command.getDateAndTime(args.get(2));
            Task task = new Event(args.get(0), from, to);
            taskList.addTask(task);
            dataStorage.saveTasksToStorage(taskList);
            return Display.addTask(task, taskList);
        } catch (IndexOutOfBoundsException error) {
            throw new BuddyInvalidCommandArgumentsException("Please enter event command in the"
                    + " following format \n `event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new BuddyInvalidDateFormatException("Please enter the date format of event command as follows: \n"
                    + "yyyy-MM-dd HHmm (e.g 2000-02-02 1400)");
        }
    }
}

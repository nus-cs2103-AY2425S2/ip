package buddy.command;

import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandArgumentsException;
import buddy.exception.BuddyTaskNotFoundException;
import buddy.storage.DataStorage;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represents the type Unmark command.
 */
public class UnmarkCommand extends Command {

    /**
     * Instantiates a new Unmark command.
     *
     * @param args the args from user
     */
    public UnmarkCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        try {
            Task task = taskList.getTask(Integer.parseInt(args.get(0)) - 1);
            task.unmarkTaskAsDone();
            dataStorage.saveTasksToStorage(taskList);
            return Display.unmarkTask(task);
        } catch (NumberFormatException error) {
            throw new BuddyInvalidCommandArgumentsException("Your task id needs to be a number");
        } catch (IndexOutOfBoundsException error) {
            throw new BuddyTaskNotFoundException(taskList.getLength());
        }
    }
}

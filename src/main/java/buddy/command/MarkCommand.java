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
 * Represents the type Mark command.
 */
public class MarkCommand extends Command {

    /**
     * Instantiates a new Mark command.
     *
     * @param args the args from the user
     */
    public MarkCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        try {
            Task task = taskList.getTask(Integer.parseInt(args.get(0)) - 1);
            task.markTaskAsDone();
            dataStorage.saveTasksToStorage(taskList);
            return Display.markTask(task);
        } catch (NumberFormatException error) {
            throw new BuddyInvalidCommandArgumentsException("Your task id needs to be a number");
        } catch (IndexOutOfBoundsException error) {
            throw new BuddyTaskNotFoundException(taskList.getLength());
        }
    }
}

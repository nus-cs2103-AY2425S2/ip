package buddy.command;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandArgumentsException;
import buddy.exception.BuddyTaskNotFoundException;
import buddy.storage.DataStorage;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represents the type Delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Instantiates a new Delete command.
     *
     * @param args the args
     */
    public DeleteCommand(java.util.ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        try {
            int taskId = Integer.parseInt(args.get(0));
            Task taskToDelete = taskList.getTask(Integer.parseInt(args.get(0)) - 1);
            taskList.deleteTask(taskToDelete);
            dataStorage.saveTasksToStorage(taskList);
            return Display.deleteTask(taskToDelete, taskList);
        } catch (NumberFormatException error) {
            throw new BuddyInvalidCommandArgumentsException("Your task id needs to be a number.");
        } catch (IndexOutOfBoundsException error) {
            throw new BuddyTaskNotFoundException(taskList.getLength());
        }
    }
}

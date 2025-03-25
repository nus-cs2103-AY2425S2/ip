package buddy.command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidDateFormatException;
import buddy.exception.BuddyTaskNotFoundException;
import buddy.storage.DataStorage;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represents the type Update command.
 */
public class UpdateCommand extends Command {

    /**
     * Instantiates a new Update command.
     *
     * @param args the args
     */
    public UpdateCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        if (args.size() < 3) {
            throw new BuddyException("Invalid update format! Use: update <taskId> /<field> <newValue>");
        }

        try {
            int taskId = Integer.parseInt(args.get(0)) - 1;
            if (taskId < 0 || taskId >= taskList.getLength()) {
                throw new BuddyTaskNotFoundException(taskList.getLength());
            }

            String field = args.get(1);
            String newValue = args.get(2);

            Task task = taskList.getTask(taskId);

            task.updateTask(field, newValue);

            dataStorage.saveTasksToStorage(taskList);

            return Display.updateTask(task);

        } catch (NumberFormatException e) {
            throw new BuddyException("Task ID must be a number!");
        } catch (DateTimeParseException e) {
            throw new BuddyInvalidDateFormatException("Please enter the date format as follows: \n"
                    + "yyyy-MM-dd HHmm (e.g 2000-02-02 1400)");
        }
    }

}

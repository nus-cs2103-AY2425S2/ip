package buddy.command;

import java.util.ArrayList;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.storage.DataStorage;
import buddy.task.Task;
import buddy.task.TaskList;
import buddy.task.Todo;

/**
 * Represents the type Add todo command.
 */
public class AddTodoCommand extends Command {
    /**
     * Instantiates a new Add todo command.
     *
     * @param args the args from the user command
     */
    public AddTodoCommand(ArrayList<String> args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        Task task = new Todo(args.get(0));
        taskList.addTask(task);
        dataStorage.saveTasksToStorage(taskList);
        return Display.addTask(task, taskList);
    }
}

package buddy.command;

import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.storage.DataStorage;
import buddy.task.TaskList;

/**
 * Represents the type List tasks command.
 */
public class ListTasksCommand extends Command {

    @Override
    public String execute(TaskList taskList, DataStorage dataStorage) throws BuddyException {
        return Display.listTasks(taskList);
    }
}

package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     * <p>
     * This command displays all tasks in the task list.
     *
     * @param tasks   The task list to display tasks from.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save tasks to.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getListAsString();
    }
}

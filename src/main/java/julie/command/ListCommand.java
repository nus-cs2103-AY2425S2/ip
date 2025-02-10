package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;

/**
 * Represents a command to display a list of the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     * Calls the UI to display the tasks.
     *
     * @param tasks The task list containing all stored tasks.
     * @param ui The user interface to display the task list.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

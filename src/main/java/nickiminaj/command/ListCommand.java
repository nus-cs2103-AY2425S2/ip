package nickiminaj.command;

import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display the tasks.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}


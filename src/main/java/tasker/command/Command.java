package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Performs an action on a task list.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param tasks The task list to execute this command on.
     * @param storage The storage for saving changes to.
     * @return The output of executing the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TaskerException;
}

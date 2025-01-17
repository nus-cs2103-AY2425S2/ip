package tasker.command;

import tasker.task.TaskList;

/**
 * Performs an action on a task list.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param tasks The task list to execute this command on.
     * @return The output of executing the command.
     */
    public abstract String execute(TaskList tasks);
}

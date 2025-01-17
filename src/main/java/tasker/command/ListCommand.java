package tasker.command;

import tasker.task.TaskList;

/**
 * Lists the contents of the task list.
 */
class ListCommand extends Command {

    /**
     * Lists the contents of the task list.
     *
     * @param tasks The task list to list.
     * @return The contents of the task list.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}

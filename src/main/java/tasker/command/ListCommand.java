package tasker.command;

import tasker.TaskList;

/**
 * Lists the contents of the task list
 */
class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}

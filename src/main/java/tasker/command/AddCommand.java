package tasker.command;

import tasker.task.TaskList;
import tasker.task.Task;

/**
 * Adds a task to the task list.
 */
class AddCommand extends Command {
    /** Task to be added */
    private Task task;

    /**
     * Class constructor.
     * 
     * @param task Task to be added.
     */
    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param tasks The list to add the task to.
     * @return The ouput from adding a task.
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        return String.format("""
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.""",
                this.task, tasks.size());
    }
}

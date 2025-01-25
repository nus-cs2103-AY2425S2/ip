package tasker.command;

import tasker.task.TaskList;

/**
 * Command that makes use of task index.
 */
abstract class IndexCommand extends Command {
    /** Index of task to be operated on */
    int index;
    /** Notification when an invalid index is used */
    String invalidIndex = String.format("Task %d does not exist.", this.index + 1);

    /**
     * Constructor class.
     *
     * @param index The index of the task to be operated on.
     */
    IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Formats the task at the index.
     *
     * @param tasks The task list to get the task from.
     * @return The description of the task at the index.
     */
    public String getIndexTask(TaskList tasks) {
        return "  " + tasks.getTaskDescription(this.index);
    }
}

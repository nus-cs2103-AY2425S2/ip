package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Command that makes use of task index.
 */
abstract class IndexCommand extends Command {
    /** Index of task to be operated on */
    protected int index;
    /** Notification when an invalid index is used */
    protected String invalidIndex = String.format("Task %d does not exist.", this.index + 1);

    /**
     * Constructor class.
     *
     * @param index The index of the task to be operated on.
     */
    IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Pads the task at the index.
     *
     * @param tasks The task list to get the task from.
     * @param storage The storage for saving changes to.
     * @return The description of the task at the index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskerException {
        return "  " + tasks.getTaskDescription(this.index);
    }
}

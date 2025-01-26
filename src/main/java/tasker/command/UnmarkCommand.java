package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Command for unmarking a task as done.
 */
public class UnmarkCommand extends IndexCommand {
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Unmarks the task with the corresponding index as done.
     *
     * @param tasks The list of tasks to unmark from.
     * @return Output of unmarking the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskerException {
        int index = this.getIndex();

        if (!tasks.isValidIndex(index)) {
            return this.getInvalidIndex();
        }

        tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n" + this.getIndexTaskDescription(tasks);
    }
}

package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Command for marking a task as done.
 */
public class MarkCommand extends IndexCommand {
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Marks the task with the corresponding index as done.
     *
     * @param tasks The list of tasks to mark from.
     * @return Output of marking the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskerException {
        int index = this.getIndex();

        if (!tasks.isValidIndex(index)) {
            return this.getInvalidIndex();
        }

        tasks.markTask(index);
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n" + this.getIndexTaskDescription(tasks);
    }
}

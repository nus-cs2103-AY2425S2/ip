package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Command for marking a task as done.
 */
class MarkCommand extends IndexCommand {
    MarkCommand(int index) {
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
        if (!tasks.isValidIndex(this.index)) {
            return this.invalidIndex;
        }

        tasks.markTask(this.index);
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n" + this.getIndexTask(tasks);
    }
}

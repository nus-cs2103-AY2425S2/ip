package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Delete a task.
 */
public class DeleteCommand extends IndexCommand {
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes the task at the index.
     *
     * @param tasks The list of tasks to delete from.
     * @return Output of deleting the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskerException {
        int index = this.getIndex();

        if (!tasks.isValidIndex(index)) {
            return this.getInvalidIndex();
        }

        String task = this.getIndexTaskDescription(tasks);
        String response = tasks.delete(index);
        storage.save(tasks);
        return String.format("""
                Noted. I've removed this task:
                %s
                %s""", task, response);
    }
}

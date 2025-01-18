package tasker.command;

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
    public String execute(TaskList tasks) {
        return String.format("""
                Noted. I've removed this task:
                %s
                %s""",
                super.execute(tasks), tasks.delete(this.index));
    }
}

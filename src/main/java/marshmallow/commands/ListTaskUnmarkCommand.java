package marshmallow.commands;

import marshmallow.tasks.Task;
import marshmallow.tasks.TaskList;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class ListTaskUnmarkCommand extends Command {
    private int idx;

    /**
     * Constructs a command to mark a task as not done in the task list.
     *
     * @param idx The index of the task to unmark.
     */
    public ListTaskUnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String handle(TaskList tasks) {
        assert tasks != null : "Task list should not be null";

        Task task = tasks.getTask(idx);
        task.unmark();
        tasks.save();
        return String.format("OK, I've marked this task as not done yet:\n  %s", task);
    }
}

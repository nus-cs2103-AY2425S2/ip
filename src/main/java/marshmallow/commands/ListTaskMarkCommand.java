package marshmallow.commands;

import marshmallow.tasks.Task;
import marshmallow.tasks.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class ListTaskMarkCommand extends Command {
    private int idx;

    /**
     * Constructs a command to mark a task as done in the task list.
     *
     * @param idx The index of the task to mark.
     */
    public ListTaskMarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String handle(TaskList tasks) {
        assert tasks != null : "Task list should not be null";

        Task task = tasks.getTask(idx);
        task.mark();
        tasks.save();
        return String.format("Nice! I've marked this task as done:\n  %s", task);
    }
}

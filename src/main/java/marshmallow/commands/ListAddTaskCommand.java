package marshmallow.commands;

import marshmallow.tasks.Task;
import marshmallow.tasks.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public abstract class ListAddTaskCommand extends Command {
    protected Task task;

    @Override
    public String handle(TaskList tasks) {
        assert tasks != null : "Task list should not be null";
        assert task != null : "Task should not be null";

        tasks.addTask(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                tasks.count());
    }
}

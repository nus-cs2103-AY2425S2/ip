package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Task;


/**
 * Represents a command to set the priority of a task.
 */
public class SetPriorityCommand extends Command {
    private final int index;
    private final Task.Priority priority;

    /**
     * Constructs a {@code SetPriorityCommand} with the given task index and priority level.
     *
     * @param index The 1-based index of the task.
     * @param priority The priority level (H, M, L).
     */
    public SetPriorityCommand(int index, Task.Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        validateIndex(index, tasks);

        tasks.getTask(index).setPriority(priority);
        storage.saveTasks(tasks.getAllTasks());
        ui.showMessage("Okay! Priority for task " + index + " set to " + priority + ".");
    }
}


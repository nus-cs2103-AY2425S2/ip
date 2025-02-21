package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task.
     * The task is removed from the task list, saved to storage, and acknowledged via the UI.
     *
     * @param tasks The task list from which the task will be removed.
     * @param ui The user interface to display feedback to the user.
     * @param storage The storage system to persist the updated task list.
     * @throws WrongFormatException If the provided index is out of bounds (i.e., task does not exist).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        validateIndex(index, tasks);

        Task removedTask = tasks.getTask(index - 1);
        tasks.deleteTask(index - 1);
        storage.saveTasks(tasks.getAllTasks());
        ui.deleteMessage(removedTask, tasks.size());
    }
}

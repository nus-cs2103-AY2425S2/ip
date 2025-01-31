package taskmax.command;

import taskmax.exception.TaskmaxException;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.task.Task;
import taskmax.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param index The one-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index - 1; // Convert one-based index to zero-based index.
    }

    /**
     * Executes the delete command by removing the specified task and displaying a confirmation message.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates (not used here).
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If the index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        Task removedTask = tasks.removeTask(index);
        ui.showMessage("Noted. I've removed this task:\n"
                + removedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return false;
    }
}

package taskmax.command;

import taskmax.exception.TaskmaxException;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param index The one-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index - 1; // Convert one-based index to zero-based index.
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates.
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If the index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        tasks.markTask(index, true);
        ui.showMessage("Nice! I've marked your task as done.\n"
                + "Keep up the good work!");
        return false;
    }
}

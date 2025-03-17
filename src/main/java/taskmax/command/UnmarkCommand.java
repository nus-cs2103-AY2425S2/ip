package taskmax.command;

import taskmax.exception.TaskmaxException;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the given task index.
     *
     * @param index The one-based index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        assert index >= 0 : "Index should not be negative";
        this.index = index - 1;
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates.
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If the index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        String response = executeForGUI(tasks, storage);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the unmark command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler for saving task updates.
     * @return A string response confirming the task has been unmarked.
     * @throws TaskmaxException If the index is out of bounds.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        assert tasks != null : "Task list should not be null";
        tasks.markTask(index, false);
        return Ui.LINE
                + "\nI've unmarked your task.\n"
                + "Don't give up on it yet!\n"
                + Ui.LINE;
    }
}

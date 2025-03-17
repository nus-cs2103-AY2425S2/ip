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
        assert index >= 0 : "Index should not be negative";
        this.index = index - 1;
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
        String response = executeForGUI(tasks, storage);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the mark command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler for saving task updates.
     * @return A string response confirming the task has been marked as done.
     * @throws TaskmaxException If the index is out of bounds.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        assert tasks != null : "Task list should not be null";
        tasks.markTask(index, true);
        return Ui.LINE
                + "\nNice! I've marked your task as done.\n"
                + "Keep up the good work!\n"
                + Ui.LINE;
    }
}

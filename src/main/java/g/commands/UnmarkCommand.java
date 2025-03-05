package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

/**
 * Represents a command to unmark a task as completed.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to mark a task as not done.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The UI to display messages.
     * @param storage The storage to update after unmarking the task.
     * @return The formatted message indicating success or failure.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.unmarkTask(index);
            storage.save(tasks.getTasks());
            return ui.displaySuccessfulUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Invalid task index for unmarking.");
        }
    }
}

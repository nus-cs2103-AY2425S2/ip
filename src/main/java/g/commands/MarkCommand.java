package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command to mark a task as done.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The UI to display messages.
     * @param storage The storage to update after marking the task.
     * @return The formatted message indicating success or failure.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTask(index);
            storage.save(tasks.getTasks());
            return ui.displaySuccessfulMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Invalid task index for marking as done.");
        }
    }
}

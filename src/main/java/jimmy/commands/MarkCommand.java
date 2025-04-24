package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.Task;
import jimmy.tasks.TaskList;

/**
 * The {@code MarkCommand} class represents a command to mark a specific task
 * as completed in the task list. This command updates the task's status,
 * saves the changes to storage, and notifies the user via the UI.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param index the 1-based index of the task to be marked as completed, provided as a string.
     * @throws JimmyException if the provided index is not a valid integer.
     */
    public MarkCommand(String index) throws JimmyException {
        super();
        try {
            this.index = Integer.parseInt(index) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new JimmyException("Invalid index for mark command.");
        }
    }

    /**
     * Executes the mark command by marking the specified task as completed,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   the {@code TaskList} containing all current tasks.
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance responsible for saving tasks.
     * @throws JimmyException if the task index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JimmyException {
        assert tasks != null : "TaskList should not be null";
        if (index < 0 || index >= tasks.size()) {
            throw new JimmyException("Task index out of bounds.");
        }
        Task task = tasks.getTask(index);
        task.mark();
        storage.save(tasks.getAllTasks());
        ui.showMessage("Nice! I've marked this task as done:\n  " + task);
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the mark command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

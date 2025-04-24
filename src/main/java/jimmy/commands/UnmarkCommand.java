package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.Task;
import jimmy.tasks.TaskList;

/**
 * The {@code UnmarkCommand} class represents a command to unmark a specific task
 * as not done in the task list. This command is typically triggered when a user
 * wants to mark a task as incomplete after previously marking it as done.
 *
 * <p>It parses the provided task index, validates it, updates the task status,
 * saves the updated task list to storage, and notifies the user via the UI.</p>
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param index the 1-based index of the task to be unmarked (provided as a string).
     * @throws JimmyException if the provided index is not a valid integer.
     */
    public UnmarkCommand(String index) throws JimmyException {
        super();
        try {
            this.index = Integer.parseInt(index) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new JimmyException("Invalid index for unmark command.");
        }
    }

    /**
     * Executes the unmark command by unmarking the specified task as not done,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   the {@code TaskList} containing all current tasks.
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance responsible for saving tasks.
     * @throws JimmyException if the task index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JimmyException {
        if (index < 0 || index >= tasks.size()) {
            throw new JimmyException("Task index out of bounds.");
        }
        Task task = tasks.getTask(index);
        task.unmark();
        storage.save(tasks.getAllTasks());
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the unmark command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

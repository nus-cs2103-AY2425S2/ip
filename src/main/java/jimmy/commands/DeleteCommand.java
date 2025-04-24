package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.Task;
import jimmy.tasks.TaskList;

/**
 * The {@code DeleteCommand} class represents a command to delete a specific task
 * from the task list. It removes the task, updates the storage, and notifies the user.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index the 1-based index of the task to be deleted, provided as a string.
     * @throws JimmyException if the provided index is not a valid integer.
     */
    public DeleteCommand(String index) throws JimmyException {
        super();
        try {
            this.index = Integer.parseInt(index) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new JimmyException("Invalid index for delete command.");
        }
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
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
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks.getAllTasks());
        ui.showMessage("Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return "Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the delete command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package perrygpt.commands;

import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.tasks.Task;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index to unmark.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by unmarking a task as not done, updating storage, and returning a success message.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving the updated task list.
     * @return A success message indicating that the task has been unmarked successfully.
     * @throws PerryGPTException If the task index is invalid.
     * @throws IOException   If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PerryGPTException, IOException {
        if (index < 0 || index >= tasks.getAllTasks().size()) {
            throw new PerryGPTException("Invalid task index.");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        try {
            storage.save(tasks.getAllTasks());
            return ui.showSuccess("Unmarked task as not done: " + task);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}

package perrygpt.commands;

import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.tasks.Task;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task index to mark as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking a task as done, updating storage, and returning a success message.
     *
     * @param tasks   The task list containing the task to be marked as done.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving the updated task list.
     * @return A success message indicating that the task has been marked as done.
     * @throws PerryGPTException If the task index is invalid.
     * @throws IOException   If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PerryGPTException, IOException {
        if (index < 0 || index >= tasks.getAllTasks().size()) {
            throw new PerryGPTException("Invalid task index.");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        try {
            storage.save(tasks.getAllTasks());
            return ui.showSuccess("Marked task as done: " + task);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
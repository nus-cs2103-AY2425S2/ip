package commands;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand.
     * @param input The full user input containing the task number to delete.
     * @throws IOException If the task number is invalid.
     */
    public DeleteCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IOException("Delete what?? Task number required.");
        }
        try {
            this.taskIndex = Integer.parseInt(parts[1]) - 1; // Convert 1-based index to 0-based
        } catch (NumberFormatException e) {
            throw new IOException("Task number must be an integer!!");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IOException(" Task number out of range..");
        }
        Task removedTask = tasks.delete(taskIndex);
        return "Noted. I've removed this task:\n   "
                + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

package commands;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to edit a task's description.
 * <p>
 * Expected input format: "edit <task-number> <new-description>"
 * For example: "edit 2 Buy milk and bread" will edit task #2's description.
 * </p>
 */
public class EditCommand extends Command {
    private final int taskIndex;
    private final String newDescription;

    /**
     * Constructs an EditCommand by parsing the user input.
     * @param input The full user input.
     * @throws IOException If the input format is invalid.
     */
    public EditCommand(String input) throws IOException {
        // Split into three parts: "edit", task-number, new-description
        String[] parts = input.split(" ", 3);
        if (parts.length < 3) {
            throw new IOException(" Invalid edit command format. " +
                    "Expected: edit <task-number> <new-description>");
        }
        try {
            this.taskIndex = Integer.parseInt(parts[1]) - 1; // convert to 0-based index
        } catch (NumberFormatException e) {
            throw new IOException("Task number must be an integer.");
        }
        this.newDescription = parts[2];
    }

    /**
     * Executes the edit command: edits the specified task's description and saves the changes.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage system.
     * @throws IOException If the task index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IOException("Task number out of range.");
        }
        // Retrieve the task to be updated. Ensure TaskList provides a getter (e.g., get(int index)).
        Task task = tasks.get(taskIndex);
        // Update the description. Ensure your Task class has a setter for description.
        task.setDescription(newDescription);
        // Save the updated task list.
        storage.save(tasks.getTasks());
        return "Task edited: " + task;
    }
}

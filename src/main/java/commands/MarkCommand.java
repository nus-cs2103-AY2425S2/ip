package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 * Expected input format: "mark <task-number>"
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand by parsing the user input.
     * @param input The full user input.
     * @throws IOException If the input format is invalid.
     */
    public MarkCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IOException("Missing task number. Expected: mark <task-number>");
        }
        try {
            // Use the task number as 1-based (do not subtract 1 here)
            this.taskIndex = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IOException("Task number must be an integer.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new IOException("Task number out of range.");
        }

        // Check if the task is already marked
        Task task = tasks.get(taskIndex - 1); // Assuming getTask returns the task at 1-based index
        if (task.getStatusIcon().equals("X")) {
            throw new IOException("Task is already marked as done.");
        }

        // TaskList.markTask expects a 1-based index and subtracts 1 when accessing the list.
        task = tasks.markTask(taskIndex);
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

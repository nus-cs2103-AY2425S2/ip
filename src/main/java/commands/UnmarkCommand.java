package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.io.IOException;

/**
 * Represents a command to mark a task as not done.
 * Expected input format: "unmark <task-number>"
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IOException("Missing task number. Expected: unmark <task-number>");
        }

        try {
            // Use 1-based indexing here as well.
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

        Task task = tasks.get(taskIndex - 1);
        if (task.getStatusIcon().equals(" ")) {
            throw new IOException("Task is not done yet.");
        }

        task = tasks.unmarkTask(taskIndex);
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

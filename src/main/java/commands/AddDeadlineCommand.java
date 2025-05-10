package commands;

import java.io.IOException;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand.
     * @param input The full user input containing the deadline description and date.
     * @throws IOException If the format is incorrect.
     */
    public AddDeadlineCommand(String input) throws IOException {
        if (!input.contains("/by ")) {
            throw new IOException("Deadline format: deadline <desc> /by yyyy-MM-dd HHmm");
        }
        String[] parts = input.replaceFirst("deadline ", "").split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new IOException("Deadline format: deadline <desc> /by yyyy-MM-dd HHmm");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            tasks.add(new Deadline(description, by));
            return "Got it. I've added this task:\n   " + tasks.getLastTask()
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid date format :( - Try using yyyy-MM-dd HHmm.");
        }
    }
}

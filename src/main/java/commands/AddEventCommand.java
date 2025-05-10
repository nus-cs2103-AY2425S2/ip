package commands;

import java.io.IOException;
import java.time.LocalDateTime;

import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add an Event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand.
     * @param input The full user input containing the event description and dates.
     * @throws IOException If the format is incorrect.
     */
    public AddEventCommand(String input) throws IOException {
        if (!input.contains("/from ") || !input.contains("/to ")) {
            throw new IOException("Event format: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] parts = input.replaceFirst("event ", "").split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            throw new IOException("Event format: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        LocalDateTime start = Task.parseDate(from);
        LocalDateTime end = Task.parseDate(to);
        if (start == null || end == null) {
            throw new IOException("Invalid date format :( - Try using yyyy-MM-dd HHmm.");
        }
        if (start.isAfter(end)) {
            throw new IOException("Start date must be before end date.");
        }
        try {
            tasks.add(new Event(description, from, to));
            return "Got it. I've added this task:\n   " + tasks.getLastTask() +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid date format :( - Try using yyyy-MM-dd HHmm.");
        }
    }
}

package nyanko.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import nyanko.storage.Storage;
import nyanko.task.Event;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to create and add an {@link Event} task to the task list.
 * The event task requires a description, a start time, and an end time.
 * The expected format for user input is:
 * <pre>
 * event description\start_time\end_time
 * </pre>
 * Example usage:
 * <pre>
 * event Meeting\2024-09-09 0900\2024-09-10 0900
 * </pre>
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an {@code EventCommand} with the given event description, start time, and end time.
     *
     * @param argument The full event input string containing the description, start time, end time,
     *                 separated by backslashes.
     * @throws IllegalArgumentException If the input format is incorrect.
     */
    public EventCommand(String argument) {
        String[] parts = argument.split("\\\\", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid event format! Use: event description\\start_time\\end_time");
        }
        if (parts[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Your description cannot be empty dumbdumb!!");
        }
        if (parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid event format! Use: event description\\start_time\\end_time");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the command, creating an {@link Event} and adding it to the task list.
     * If the provided date format is incorrect, an error message is displayed.
     *
     * @param tasks   The {@link TaskList} to which the event will be added.
     * @param ui      The {@link Ui} for user interaction.
     * @param storage The {@link Storage} system for persisting tasks.
     * @throws IOException If an error occurs during storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            String message = "WOW you're so hardworking\n"
                    + "ok fine... your task has been added!\nadded: "
                    + event.toString()
                    + "\nOh my! You have " + tasks.size() + " tasks!";
            ui.showMessage(message);
            storage.save(tasks.getTasks());
        } catch (DateTimeParseException e) {
            ui.showError("Your date/time format is incorrect! Don't be dumb! Use yyyy-MM-dd HHmm!");
        }
    }
}

package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.Event;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String argument) {
        String[] parts = argument.split("\\\\", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid event format! Use: event description\\start_time\\end_time");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the command, creating an {@link Event} and adding it to the task list.
     *
     * @param tasks   The task list.
     * @param ui      The UI for user interaction.
     * @param storage The storage system to persist tasks.
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
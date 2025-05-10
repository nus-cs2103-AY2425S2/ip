package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.Event;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to snooze an {@link Event} task by updating its start and end times.
 */
public class SnoozeEventCommand extends Command {
    private int index;
    private String newStartDateTime;
    private String newEndDateTime;

    /**
     * Constructs a {@code SnoozeEventCommand} with the specified task index and new start and end times.
     *
     * @param argument The input string containing the task index, new start time, and new end time.
     *                 Format: "task_number new_start_date_time new_end_date_time".
     * @throws IllegalArgumentException If the input format is invalid.
     */
    public SnoozeEventCommand(String argument) {
        String[] args = argument.split(" ", 3);
        if (args.length < 3) {
            throw new IllegalArgumentException(
                "Invalid snoozeEvent command! Usage: snoozeEvent <task_number> <new_start_date_time> "
                + "<new_end_date_time>"
            );
        }
        this.index = Integer.parseInt(args[0]) - 1; // Convert to 0-based index
        this.newStartDateTime = args[1];
        this.newEndDateTime = args[2];
    }

    /**
     * Executes the snooze command by updating the start and end times of the specified {@link Event} task.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance for user interaction.
     * @param storage The {@link Storage} instance for saving the updated task list.
     * @throws IOException If an error occurs while saving the task list.
     * @throws InvalidTaskNumberException If the specified task number is invalid.
     * @throws IllegalArgumentException If the specified task is not an {@link Event}.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }

        if (!(tasks.getTask(index) instanceof Event)) {
            throw new IllegalArgumentException("Task " + (index + 1) + " is not an Event!");
        }

        Event event = (Event) tasks.getTask(index);
        String oldStartDateTime = event.getFrom();
        String oldEndDateTime = event.getTo();
        event.snooze(newStartDateTime, newEndDateTime);

        storage.save(tasks.getTasks());

        ui.showMessage("Event snoozed successfully!\n"
                + "Previous Start Date/Time: " + oldStartDateTime + "\n"
                + "Previous End Date/Time: " + oldEndDateTime + "\n"
                + "Updated Task: " + event.toString());
    }
}

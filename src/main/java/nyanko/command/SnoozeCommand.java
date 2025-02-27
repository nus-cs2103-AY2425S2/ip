package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.Deadline;
import nyanko.task.Event;
import nyanko.task.Task;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to snooze (postpone/reschedule) a Deadline or Event task.
 */
public class SnoozeCommand extends Command {
    private int index;
    private String newDateTime;

    /**
     * Constructs a SnoozeCommand with the task index and new date/time.
     *
     * @param argument The task index and new date-time separated by a space.
     *                 Example: "2 2024-12-01 1800"
     */
    public SnoozeCommand(String argument) {
        String[] args = argument.split(" ", 2);
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid snooze command! Usage: snooze <task_number> <new_date_time>");
        }
        this.index = Integer.parseInt(args[0]) - 1; // Convert to 0-based index
        this.newDateTime = args[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }

        Task task = tasks.getTask(index);
        String oldDateTime = "";

        if (task instanceof Deadline) {
            oldDateTime = ((Deadline) task).getBy();
            ((Deadline) task).snooze(newDateTime);
        } else if (task instanceof Event) {
            oldDateTime = ((Event) task).getFrom();
            ((Event) task).snooze(newDateTime);
        } else {
            ui.showError("Only Deadlines and Events can be postponed!");
            return;
        }

        storage.save(tasks.getTasks());

        ui.showMessage("Task snoozed successfully!\n" +
                "Previous Date/Time: " + oldDateTime + "\n" +
                "Updated Task: " + task.toString());
    }
}

package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.Event;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

public class SnoozeEventCommand extends Command {
    private int index;
    private String newStartDateTime;
    private String newEndDateTime;

    public SnoozeEventCommand(String argument) {
        String[] args = argument.split(" ", 3);
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid snoozeEvent command! Usage: snoozeEvent <task_number> <new_start_date_time> <new_end_date_time>");
        }
        this.index = Integer.parseInt(args[0]) - 1; // Convert to 0-based index
        this.newStartDateTime = args[1];
        this.newEndDateTime = args[2];
    }

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

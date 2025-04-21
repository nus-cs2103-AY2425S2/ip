package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.Deadline;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

public class SnoozeDeadlineCommand extends Command {
    private int index;
    private String newDateTime;

    public SnoozeDeadlineCommand(String argument) {
        String[] args = argument.split(" ", 2);
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid snoozeDeadline command! Usage: snoozeDeadline <task_number> <new_date_time>");
        }
        this.index = Integer.parseInt(args[0]) - 1; // Convert to 0-based index
        this.newDateTime = args[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Oi! Task number " + (index + 1) + " is invalid!!");
        }

        if (!(tasks.getTask(index) instanceof Deadline)) {
            throw new IllegalArgumentException("Task " + (index + 1) + " is not a Deadline!");
        }

        Deadline deadline = (Deadline) tasks.getTask(index);
        String oldDateTime = deadline.getBy();
        deadline.snooze(newDateTime);

        storage.save(tasks.getTasks());

        ui.showMessage("Deadline snoozed successfully!\n"
                + "Previous Date/Time: " + oldDateTime + "\n"
                + "Updated Task: " + deadline.toString());
    }
}

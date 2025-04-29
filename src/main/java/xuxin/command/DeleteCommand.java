package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;
import xuxin.exception.DukeException;
import xuxin.task.Task;

/**
 * DeleteCommand is a command to remove a task from the tasklist.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String command) throws DukeException {
        this.index = parseTaskIndex(command, "delete");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        Statistics stats) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("OOPS!!! The index to delete cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        Task deletedTask = tasks.getTask(index);
        tasks.removeTask(index);
        stats.deleteTask(deletedTask);
        ui.addMessage("Noted. I've removed this task:\n  " + deletedTask);
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }

    private static int parseTaskIndex(String command, String action) throws DukeException {
        String[] parts = command.split(" ");
        if (parts.length < 2) throw new DukeException("Please specify a task number to " + action + ".");
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("task.Task number must be a valid integer.");
        }
    }
}


package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.task.Task;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1; // Convert to zero-based index
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        Task removedTask = tasks.removeTask(index);
        ui.showMessage("Noted. I've removed this task:\n"
                + removedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return false;
    }
}

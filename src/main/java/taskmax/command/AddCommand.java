package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.task.Task;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;


public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return false;
    }
}

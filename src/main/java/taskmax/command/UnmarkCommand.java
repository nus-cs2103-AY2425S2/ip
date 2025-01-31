package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1; // Convert to zero-based index
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        tasks.markTask(index, false);
        ui.showMessage("I've unmarked your task.\n"
                + "Don't give up on it yet!");
        return false;
    }
}

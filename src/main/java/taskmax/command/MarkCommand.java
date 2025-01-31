package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1; // Convert to zero-based index
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        tasks.markTask(index, true);
        ui.showMessage("Nice! I've marked your task as done.\n"
                + "Keep up the good work!");
        return false;
    }
}

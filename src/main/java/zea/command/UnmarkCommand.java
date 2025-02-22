package zea.command;

import zea.Storage;
import zea.ZeaException;
import zea.task.Tasks;
import zea.ui.Ui;

/**
 * A command for marking a task as done
 */
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        if (this.idx < 0 || this.idx >= tasks.getTotalTasks()) {
            throw new ZeaException("Invalid index. Please choose an index between 1 and " + tasks.getTotalTasks());
        }
        tasks.unmark(this.idx);
        storage.save(tasks);
        return Ui.formatItem("Ok, I've marked this task as not done yet:", tasks.getTask(idx), "");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return idx == ((UnmarkCommand) other).idx;
    }
}

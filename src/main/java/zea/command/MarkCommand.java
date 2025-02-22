package zea.command;

import zea.Storage;
import zea.ZeaException;
import zea.task.Tasks;
import zea.ui.Ui;

/**
 * A command for marking a task as done
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    private boolean isValidIndex(int idx, Tasks tasks) {
        return idx >= 0 && idx < tasks.getTotalTasks();
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        if (!isValidIndex(idx, tasks)) {
            throw new ZeaException("Invalid index. Please choose an index between 1 and " + tasks.getTotalTasks());
        }
        tasks.mark(this.idx);
        storage.save(tasks);
        return Ui.formatItem("Nice! I've marked this task as done:", tasks.getTask(idx), "");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return idx == ((MarkCommand) other).idx;
    }
}

package stonks.command;

import stonks.storage.Storage;
import stonks.task.TaskManager;

/**
 * Mark required task as incomplete
 */
public class UnmarkCommand extends Command {
    private final int index;
    private static final String MESSAGE = "     OK, I've marked this task as not done yet:\n       ";

    public UnmarkCommand(int index) {
        assert index >= 0 : "Task index cannot be negative";
        this.index = index;
    }

    @Override
    public String execute(TaskManager tm, Storage storage) {
        assert tm.getTasks().size() > index : "Task index out of bounds";
        tm.unmark(index);
        storage.save(tm.getTasks());
        return (MESSAGE + tm.unmark(index));
    }
}

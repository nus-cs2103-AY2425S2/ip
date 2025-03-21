package tyler.command;

import tyler.storage.Storage;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents an abstract command.
 */
public abstract class Command {
    private final boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Represents an action to perform on the list of tasks and then returns the list.
     *
     * @param tasks The list of tasks which the command should be operated on.
     * @param ui The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk.
     * @return The transformed list.
     */
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}

package alex.command;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * The general command class
 */
public abstract class Command {
    /**
     * Executes the command
     * @param tasks the task list to operate on
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns if the command is a exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }
}

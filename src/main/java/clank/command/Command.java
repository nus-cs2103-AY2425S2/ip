package clank.command;

import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command, performing operations on the task list, UI, and storage.
     *
     * @param taskList The task list to modify.
     * @param ui The UI instance for user interaction.
     * @param storage The storage system for saving/loading tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determines if the command should terminate the application.
     *
     * @return {@code false} by default. Subclasses may override to return {@code true}.
     */
    public boolean isExit() {
        return false;
    }
}

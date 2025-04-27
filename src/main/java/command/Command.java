package command;

import dew.DewException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Command class serves as an abstract base class for all commands in the Dew application.
 * It defines methods that must be implemented by concrete command classes.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The task list to be modified.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving and loading tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DewException;

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public abstract boolean isExit();
}

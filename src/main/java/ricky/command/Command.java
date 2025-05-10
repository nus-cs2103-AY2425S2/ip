/**
 * Represents the base class for all commands.
 * An abstract base class representing a command to be executed.
 * Subclasses should provide specific implementations for the command logic.
 */
package ricky.command;

import ricky.RickyException;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents an abstract command to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     * Subclasses must implement this method to define specific command behavior.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save the task list.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws RickyException;
}


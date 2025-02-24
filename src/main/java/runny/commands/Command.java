package runny.commands;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;

/**
 * Implements class-defined specific actions for each command.
 */

public interface Command {
    /**
     * Executes the command with user interface, storage, specified task list.
     *
     * @param ui      user interface for interacting with users.
     * @param storage The storage for writing and reading data.
     * @param tasks   The list of tasks
     * @throws RunnyException If an error occurs during command execution.
     */
    public void doCommand(Ui ui, Storage storage, TaskList tasks) throws RunnyException;

    /**
     * Loads task-related data for the command.
     *
     * @param tasks The list of task
     */
    public void loadTask(TaskList tasks) throws RunnyException;

    /**
     * Reverts the effect of the executed command.
     *
     * @param tasks The list of tasks to which task data can be reverted.
     * @return The command to be executed.
     * @throws RunnyException If an error occurs during command execution.
     */
    Command undoTask(TaskList tasks) throws RunnyException;

}

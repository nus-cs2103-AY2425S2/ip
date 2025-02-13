package zoe.command;

import zoe.ZoeException;
import zoe.storage.Storage;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Represents a user command.
 * Classes implementing this interface define specific behavior for executing a command
 * and determining whether the application should exit.
 */
public interface Command {

    /**
     * Executes the command using the provided storage, task list, and user interface.
     *
     * @param storage  The storage object for handling file operations.
     * @param taskList The task list containing the user's current tasks.
     * @param ui       The user interface for interacting with the user.
     * @throws ZoeException If an error occurs during command execution.
     */
    void execute(Storage storage, TaskList taskList, Ui ui) throws ZoeException;

    /**
     * Determines if this command should terminate the application.
     *
     * @return True if this command should signal the application to exit, false otherwise.
     */
    boolean isExit();
}

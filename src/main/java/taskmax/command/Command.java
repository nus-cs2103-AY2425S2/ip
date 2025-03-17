package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

/**
 * Represents an abstract command in the Taskmax application.
 * All commands must implement the execute method to perform specific actions.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving or loading tasks.
     * @return A boolean indicating whether the command requires the application to terminate.
     * @throws TaskmaxException If an error occurs while executing the command.
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException;

    /**
     * Executes the command for GUI mode.
     *
     * This method is intended to be overridden by subclasses. By default, it returns
     * a message indicating that the command is not implemented for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler for saving or loading tasks.
     * @return A string response for GUI output.
     * @throws TaskmaxException If an error occurs while executing the command.
     */
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        return "GUI mode not implemented for this command.";
    }
}
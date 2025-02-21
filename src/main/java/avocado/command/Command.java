package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to be executed by the user.
 */

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @throws AvocadoException If an error occurs during the execution of the command.
     */

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

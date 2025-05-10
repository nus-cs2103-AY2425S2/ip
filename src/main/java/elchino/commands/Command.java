package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Abstract class representing a command
 * All commands must implement the execute method.
 */
public abstract class Command {
    /**
     * Executes the command
     * @param tasks TaskList containing all tasks
     * @param ui Ui object to interact with the user
     * @param storage Storage object to save tasks
     * @throws ElchinoException if an error occurs during execution
     * @return String containing the output of the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException;
}
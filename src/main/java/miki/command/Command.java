package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for Command.
     *
     * @param isExit Boolean value to indicate if the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException;

    public boolean checkExit() {
        return this.isExit;
    }
}

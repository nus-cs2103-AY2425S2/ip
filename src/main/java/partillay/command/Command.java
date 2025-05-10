package partillay.command;

import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a general command that can be executed based on user input.
 * <p>
 *     This abstract class serves as a blueprint for specific command types,
 *     such as <code>AddCommand</code> and <code>ByeCommand</code>.
 *     Subclasses must override the <code>execute(TaskList, Ui)</code> method
 *     to define their specific behaviors.
 * </p>
 */
public abstract class Command {

    /**
     * Indicates whether the application should terminate after executing this command.
     */
    protected boolean isExit = false;

    /**
     * Executes the command based on the given task list and user interface.
     * <p>
     * Each subclass must implement this method to define specific command behavior,
     * such as adding, deleting, or marking tasks.
     * </p>
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     */
    public abstract String execute(TaskList tasks, Ui ui);

    /**
     * Checks whether the application should exit after this command executes.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise
     */
    public boolean getExitPermission() {
        return isExit;
    }
}

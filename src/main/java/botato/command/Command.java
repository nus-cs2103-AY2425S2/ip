package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * The Command class represents an abstract command that can be executed by the bot.
 * It defines the common behavior for all specific commands in the bot application.
 * Each command has an execution logic and can indicate whether it causes the bot to exit.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the specific logic of the command. This method must be implemented by subclasses
     * to define the behavior for each command.
     *
     * @param tasks The task list that may be modified during command execution.
     * @param ui The user interface that may be updated based on the command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui);

    /**
     * Returns whether the command should terminate the bot application.
     * The exit condition is only true if the "bye" command was typed.
     *
     * @return boolean indicating if the command causes the bot to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}

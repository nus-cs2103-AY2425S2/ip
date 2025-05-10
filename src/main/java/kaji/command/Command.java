package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Provides a template for executing commands.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasklist) throws KajiException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

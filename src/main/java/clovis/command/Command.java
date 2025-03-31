package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.TaskList;

/**
 * The {@code Command} class represents an abstract command that executes on a task list.
 */
public abstract class Command {
    /**
     * Executes the command on the provided task list, UI, and storage.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, corresponding to the command execution and its outcome.
     * @throws ClovisException if an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException;

    /**
     * Determines if this command should exit the program.
     *
     * @return {@code false} by default, subclass may override to return {@code true} to exit program.
     */
    public boolean isExit() {
        return false;
    }
}

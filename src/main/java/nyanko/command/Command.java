package nyanko.command;

import java.io.IOException;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents an abstract command that can be executed in the Nyanko application.
 * All specific commands should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary actions on the task list,
     * UI, and storage system.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The UI instance to handle user interaction.
     * @param storage The Storage instance for saving and loading tasks.
     * @throws IOException If there is an error reading/writing to the storage.
     * @throws InvalidTaskNumberException If the task number referenced is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException;

    /**
     * Determines whether this command should terminate the application.
     * By default, it returns false, meaning the application will continue running.
     *
     * @return false by default, overridden by commands like {@link ByeCommand}.
     */
    public boolean isExit() {
        return false;
    }
}

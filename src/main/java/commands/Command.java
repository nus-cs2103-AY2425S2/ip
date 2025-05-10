package commands;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that can be executed by the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     * @param tasks The TaskList containing the current tasks.
     * @param ui The UI handling user interactions.
     * @param storage The Storage handling file operations.
     * @throws IOException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Indicates whether the command should terminate the chatbot.
     * @return true if the chatbot should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

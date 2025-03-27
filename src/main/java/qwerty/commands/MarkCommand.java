package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for marking a task as completed and saving the updated list to storage.
 */
public class MarkCommand extends BotCommand {

    private final int INDEX; // Index of the task to be marked as done.

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Executes the command by marking the task as done in the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and mark the specified task as done.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., invalid index or task marking).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.markAsDone(this.INDEX);
        storage.saveListToFile(tasks); // Saves the updated task list to storage.
    }
}

package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for unmarking a task and saving the updated list to storage.
 */
public class UnmarkCommand extends BotCommand {

    private final int INDEX; // Index of the task to be unmarked.

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public UnmarkCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Executes the command by unmarking the task as done in the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and unmark the specified task as done.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., invalid index or task unmarking).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.unmarkAsDone(this.INDEX);
        storage.saveListToFile(tasks); // Saves the updated task list to storage.
    }
}

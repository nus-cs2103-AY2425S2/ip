package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for removing a task from the task list and saving the updated list to storage.
 */
public class DeleteCommand extends BotCommand {

    private final int INDEX; // Index of the task to be deleted.

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Executes the command by deleting the task from the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and delete the specified task.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., invalid index or task deletion).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.deleteTask(this.INDEX);
        storage.saveListToFile(tasks);
    }
}

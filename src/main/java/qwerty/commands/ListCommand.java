package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to display all tasks in the task list.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for printing the current task list.
 */
public class ListCommand extends BotCommand {

    /**
     * Executes the command by printing the list of tasks.
     *
     * @param tasks The TaskList to interact with and display the tasks.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the task list (not used in this method).
     * @throws BotException If an error occurs during the execution (e.g., task list printing).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.printList();
    }
}

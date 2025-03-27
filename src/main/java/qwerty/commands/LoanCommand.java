package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to add a new Loan task.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for adding a Loan task to the task list and saving it to storage.
 */
public class LoanCommand extends BotCommand {

    private final String DESC; // Description of the Event task.

    /**
     * Constructs an LoanCommand with the specified description.
     *
     * @param desc The description of the Event task to be added.
     */
    public LoanCommand(String desc) {
        this.DESC = desc;
    }

    /**
     * Executes the command by adding the Loan task to the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and add the new Loan task.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., during task addition or saving).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addLoanToList(this.DESC); // Adds the Loan task to the list.
        storage.saveListToFile(tasks); // Saves the updated task list to storage.
    }
}

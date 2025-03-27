package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to add a new Deadline task.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for adding a Deadline task to the task list and saving it to storage.
 */
public class DeadlineCommand extends BotCommand {

    private final String DESC;

    /**
     * Constructs a DeadlineCommand with the specified description.
     *
     * @param desc The description of the Deadline task to be added.
     */
    public DeadlineCommand(String desc) {
        this.DESC = desc;
    }

    /**
     * Executes the command by adding the Deadline task to the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and add the new Deadline task.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., during task addition or saving).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addDeadlineToList(this.DESC);
        storage.saveListToFile(tasks); // Saves the updated task list to storage.
    }
}

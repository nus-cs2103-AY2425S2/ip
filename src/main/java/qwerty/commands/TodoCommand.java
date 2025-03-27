package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to add a new ToDo task.
 * Inherits from {@link BotCommand} and implements the execution logic
 * for adding a ToDo task to the task list and saving it to storage.
 */
public class TodoCommand extends BotCommand {

    private final String DESC; // Description of the ToDo task.

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param desc The description of the ToDo task to be added.
     */
    public TodoCommand(String desc) {
        this.DESC = desc;
    }

    /**
     * Executes the command by adding the ToDo task to the TaskList and saving the updated list to storage.
     *
     * @param tasks The TaskList to interact with and add the new ToDo task.
     * @param ui The Ui instance for displaying messages (currently unused in this method).
     * @param storage The Storage instance for saving the updated task list.
     * @throws BotException If an error occurs during the execution (e.g., during task addition or saving).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.addToDoToList(this.DESC);
        storage.saveListToFile(tasks); // Saves the updated task list to storage.
    }
}

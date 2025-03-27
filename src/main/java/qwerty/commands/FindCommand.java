package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends BotCommand {

    private final String KEYWORD;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.KEYWORD = keyword;
    }

    /**
     * Executes the find command to search for tasks containing the keyword.
     * The method filters the tasks and displays the results using the provided UI.
     * The task list is not mutated during the execution.
     *
     * @param tasks   the TaskList containing all the tasks
     * @param ui      the UI instance for displaying results
     * @param storage the Storage instance for interacting with storage (not used in this command)
     * @throws BotException if an error occurs during the execution of the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        tasks.printFilteredList(this.KEYWORD);
    }
}

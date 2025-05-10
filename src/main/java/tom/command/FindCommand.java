package tom.command;

import tom.exception.TomCommandException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to find all tasks matching a keyword from the task list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find all tasks matching the keyword from the task list.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws TomCommandException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TomCommandException {
        TaskList matchedTasks = tasks.findTasks(keyword);
        ui.showMessage(id, "Here are the matching tasks in your list:");

        // Let the ListCommand handle the printing of the matched tasks
        Command command = new ListCommand();
        command.setId(id);
        command.execute(matchedTasks, ui, storage);
    };

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

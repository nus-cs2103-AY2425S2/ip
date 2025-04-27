package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The FindCommand class represents a command to search for tasks containing a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param tasks   The task list to search in.
     * @param ui      The user interface for displaying results.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String responseToUser = tasks.findTasks(keyword);
        return responseToUser;
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

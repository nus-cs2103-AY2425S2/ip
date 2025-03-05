package g.commands;

import java.util.ArrayList;
import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

/**
 * Represents a command to find tasks in the task list by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks with the given keyword.
     *
     * @param tasks   The task list to search in.
     * @param ui      The UI to display search results.
     * @param storage The storage (not used in this operation).
     * @return A formatted message with the matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTask(this.keyword);
        return ui.displayFindResults(matchingTasks, keyword);
    }
}

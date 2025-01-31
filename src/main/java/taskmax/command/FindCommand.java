package taskmax.command;

import taskmax.exception.TaskmaxException;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.ui.Ui;

/**
 * Represents a command to find tasks matching a given keyword in the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the given keyword.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates (not used here).
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If an error occurs during the search.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        boolean isFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                isFound = true;
            }
        }

        if (!isFound) {
            ui.showMessage("No tasks found matching your search keyword.");
        } else {
            ui.showMessage(output.toString());
        }

        return false;
    }
}

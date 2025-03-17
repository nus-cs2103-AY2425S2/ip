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
        assert keyword != null : "Keyword should not be null";
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
        String response = executeForGUI(tasks, storage);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the find command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler (not used here).
     * @return A string containing the search results.
     * @throws TaskmaxException If an error occurs during the search.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        assert tasks != null : "Task list should not be null";
        StringBuilder output = new StringBuilder("\nHere are the matching tasks in your list:\n");
        boolean isFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                isFound = true;
            }
        }

        return isFound ? Ui.LINE + output.toString() + Ui.LINE
                : Ui.LINE + "\nNo tasks found matching your search keyword.\n" + Ui.LINE;
    }
}

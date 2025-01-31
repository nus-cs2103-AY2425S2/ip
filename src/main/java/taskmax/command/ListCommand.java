package taskmax.command;

import taskmax.exception.TaskmaxException;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates (not used here).
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If an error occurs while accessing tasks.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty! Start adding tasks to see them in the list.");
        } else {
            StringBuilder output = new StringBuilder("\nHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            ui.showMessage(output.toString());
        }
        return false;
    }
}

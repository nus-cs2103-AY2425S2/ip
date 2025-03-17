package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.task.Task;

import taskmax.ui.Ui;

import taskmax.exception.TaskmaxException;

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
        String response = executeForGUI(tasks, storage);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the list command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler (not used here).
     * @return A string containing the list of tasks.
     * @throws TaskmaxException If an error occurs while accessing tasks.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        assert tasks != null : "Task list should not be null";
        if (tasks.isEmpty()) {
            return Ui.LINE + "\nYour task list is empty! Start adding tasks to see them in the list.\n" + Ui.LINE;
        } else {
            StringBuilder output = new StringBuilder(Ui.LINE + "\nHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                output.append(i + 1)
                      .append(". ")
                      .append(task.toString())
                      .append(" (priority ")
                      .append(task.getPriority())
                      .append(")\n");
            }
            return output.toString() + Ui.LINE;
        }
    }
}

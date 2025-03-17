package taskmax.command;

import taskmax.exception.TaskmaxException;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

/**
 * Represents a command to sort tasks based on a given criterion.
 */
public class SortCommand extends Command {
    private final String criterion;

    /**
     * Constructs a SortCommand with the specified sorting criterion.
     *
     * @param criterion The criterion by which to sort tasks (e.g., "duedate").
     */
    public SortCommand(String criterion) {
        assert criterion != null && !criterion.trim().isEmpty() : "Criterion should not be null or empty";
        this.criterion = criterion.trim().toLowerCase();
    }

    /**
     * Executes the sort command by sorting the task list based on the given criterion.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates (not used here).
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If an error occurs while sorting the tasks.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        String response = sortTasks(tasks);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the sort command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler (not used here).
     * @return A string containing the sorted task list.
     * @throws TaskmaxException If an error occurs while sorting the tasks.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        return sortTasks(tasks);
    }

    /**
     * Helper method to sort tasks based on the given criterion.
     *
     * @param tasks The task list containing the tasks.
     * @return The response message after sorting the tasks.
     * @throws TaskmaxException If the sorting criterion is invalid.
     */
    private String sortTasks(TaskList tasks) throws TaskmaxException {
        assert tasks != null : "Task list should not be null";

        switch (criterion) {
            case "priority":
                tasks.sortByPriority();
                break;
            default:
                throw new TaskmaxException("Please use \"sort priority\" instead.");
        }

        StringBuilder response = new StringBuilder(Ui.LINE + "\nHere are your tasks sorted by " + criterion + ":\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString() + Ui.LINE;
    }
}

package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.task.Task;
import taskmax.ui.Ui;
import taskmax.exception.TaskmaxException;

/**
 * Represents a command to add one or more tasks to the task list.
 */
public class AddCommand extends Command {
    private final Task[] tasksToAdd;
    private final int priority;

    /**
     * Constructs an AddCommand with one or more tasks and a priority.
     *
     * @param tasksToAdd The tasks to be added to the task list.
     * @param priority   The priority to assign to the tasks.
     */
    public AddCommand(int priority, Task... tasksToAdd) {
        assert tasksToAdd != null : "Tasks to add should not be null";
        this.tasksToAdd = tasksToAdd;
        this.priority = priority;
    }

    /**
     * Executes the add command by adding the specified tasks to the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates.
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If an error occurs while adding the tasks.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        String response = addTasksToList(tasks);
        ui.showMessage(response);
        return false;
    }

    /**
     * Executes the add command for GUI mode.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage handler for saving task updates.
     * @return The response message for the GUI.
     * @throws TaskmaxException If an error occurs while adding the tasks.
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws TaskmaxException {
        return addTasksToList(tasks);
    }

    /**
     * Helper method to add tasks to the task list and return the response message.
     *
     * @param tasks The task list containing the tasks.
     * @return The response message.
     */
    private String addTasksToList(TaskList tasks) {
        assert tasks != null : "Task list should not be null";
        StringBuilder response = new StringBuilder(Ui.LINE
                + "\nGot it. I've added the following tasks:\n");
        for (Task task : tasksToAdd) {
            assert task != null : "Task should not be null";
            tasks.addTask(task);
            response.append("  ").append(task.toString()).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n" + Ui.LINE);
        return response.toString();
    }
}
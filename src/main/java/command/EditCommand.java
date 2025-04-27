package command;

import dew.DewException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The EditCommand class represents a command to edit a specific component of a task in the task list.
 */
public class EditCommand extends Command {
    private int taskIndex;
    private String component;
    private String newValue;

    /**
     * Constructs an EditCommand to modify a specific component of a task.
     *
     * @param taskIndex  The index of the task to be edited (zero-based index).
     * @param component  The component of the task to update (e.g., "description", "by", "start", "end").
     * @param newValue   The new value to assign to the specified component.
     */
    public EditCommand(int taskIndex, String component, String newValue) {
        this.taskIndex = taskIndex;
        this.component = component;
        this.newValue = newValue;
    }

    /**
     * Executes the EditCommand, updating a specific component of a task.
     * The method verifies that the task index is valid and that the update is allowed.
     * If the update is successful, the task is saved to storage.
     * If an invalid update is attempted, an error message is displayed.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage system for saving updated tasks.
     * @return A message indicating the result of the edit operation.
     * @throws DewException If the task index is out of bounds.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DewException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DewException("Invalid task index! Task number does not exist.");
        }

        Task task = tasks.get(taskIndex);
        task.updateComponent(component, newValue);

        // Save updated task list
        storage.saveTasks(tasks.getTasks());

        return "Updated task: " + task;
    }


    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as deleting a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}

package taskmax.command;

import taskmax.exception.TaskmaxException;
import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.task.Task;
import taskmax.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the specified task to the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving task updates (not used here).
     * @return False, as this command does not terminate the application.
     * @throws TaskmaxException If an error occurs while adding the task.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaskmaxException {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        return false;
    }
}

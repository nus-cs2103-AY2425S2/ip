package ujin.command;

import ujin.task.Task;
import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Represents a command to mark or unmark the task.
 * This class inherits from the {@link Command} class
 * and implements the execute behavior of marking or unmarking.
 */
public class MarkerCommand extends Command {

    /** Indicates whether to mark (true) or unmark (false) the task */
    private final Boolean MARKED;

    /** The 1-based index of the task in the task list to be marked/unmarked */
    private final int INDEX;

    /**
     * Constructs a MarkerCommand with the specified mark status and task index.
     *
     * @param marked {@code true} to mark the task as done, {@code false} to unmark it
     * @param index The 1-based index position of the task in the task list
     */
    public MarkerCommand(Boolean marked, int index) {
        this.MARKED = marked;
        this.INDEX = index;
    }

    /**
     * Executes the command by updating the task's mark status and displaying the result through the UI.
     * The task index is converted to 0-based internally for list operations.
     *
     * @param taskList The task list containing all tasks
     * @param ui The user interface handler for displaying messages
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        Task task = taskList.get(INDEX - 1);
        if (MARKED) {
            task.markAsDone();
            return ui.markTask(task);
        } else {
            task.unmarkAsDone();
            return ui.unmarkTask(task);
        }
    }
}

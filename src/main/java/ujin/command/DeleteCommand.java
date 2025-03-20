package ujin.command;

import ujin.task.Task;
import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This class inherits from the {@link Command} class and implements the execute behavior
 * for deleting tasks.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the to be deleted task.
     */
    private final int INDEX;

    /**
     * Constructs an DeleteCommand object with the specified task.
     *
     * @param index The index of the to be deleted task.
     */
    public DeleteCommand(int index) {

        this.INDEX = index;
    }

    /**
     * Executes the command by deleting the task from the task list and displaying the result through the UI.
     *
     * @param taskList The task list containing all tasks
     * @param ui       The user interface handler
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        Task task = taskList.get(INDEX - 1);
        taskList.delete(INDEX - 1);
        int size = taskList.size();
        return ui.deletedTask(task, size);
    }
}

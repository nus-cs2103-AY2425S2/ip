package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Task;

/**
 * Represents a command that marks a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The 1-based index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark the task as not done.
     * If the index is invalid, an exception is thrown.
     *
     * @param tasks The task list containing all stored tasks.
     * @param ui The user interface to display confirmation.
     * @param storage The storage system to save the updated task list.
     * @throws WrongFormatException If the provided index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        validateIndex(index, tasks);
        Task task = tasks.getTask(index - 1);
        task.markUndone();
        storage.saveTasks(tasks.getAllTasks());
        ui.unmarkMessage(task);
    }
}

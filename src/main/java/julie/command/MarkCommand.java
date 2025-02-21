package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Task;

/**
 * Represents a command that marks a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The 1-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task as done.
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
        task.markDone();
        storage.saveTasks(tasks.getAllTasks());
        ui.markMessage(task);
    }
}

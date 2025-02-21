package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.Task;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage of tasks.
     * @throws AvocadoException If an error occurs when deleting the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        Task task = tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskDeleted(task, tasks);
    }
    
}

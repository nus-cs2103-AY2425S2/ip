package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.Task;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to add a task.
 */

public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list and saves the updated task list to the storage file.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage object.
     * @throws AvocadoException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(task, tasks);
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */

    @Override
    public boolean isExit() {
        return false;
    }
    
}

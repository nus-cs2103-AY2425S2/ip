package nickiminaj.command;

import nickiminaj.TaskList;
import nickiminaj.Ui;
import nickiminaj.Storage;
import nickiminaj.tasks.Task;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Retrieves the task associated with this command.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * saving the updated task list to storage, and displaying the
     * added task to the user.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showAddedTask(task, tasks.getSize());
    }
}

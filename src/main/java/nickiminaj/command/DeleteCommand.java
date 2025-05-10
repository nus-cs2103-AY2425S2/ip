package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.TaskList;
import nickiminaj.Ui;
import nickiminaj.Storage;
import nickiminaj.tasks.Task;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * saving the updated task list to storage, and displaying the deleted
     * task to the user.
     *
     * @param tasks   The task list from which the task is removed.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws NickiMinajException If the index is invalid or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        Task removedTask = tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showDeletedTask(removedTask, tasks.getSize());
    }
}

package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;

/**
 * Represents a command that unmarks a task as completed in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to be unmarked as completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the specified task as incomplete,
     * saving the updated task list to storage, and displaying the unmarked
     * task to the user.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws NickiMinajException If the index is invalid or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkedTask(tasks.getTask(index));
    }
}

package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;


/**
 * Represents a command that marks a task as completed in the task list.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as completed.
     */
    private int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the specified task as completed,
     * saving the updated task list to storage, and displaying the marked
     * task to the user.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws NickiMinajException If the index is invalid or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedTask(tasks.getTask(index));
    }
}
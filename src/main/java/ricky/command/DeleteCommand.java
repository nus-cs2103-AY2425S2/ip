/**
 * Represents a command to delete a task from the task list.
 */
package ricky.command;

import ricky.RickyException;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the task from the task list and printing the deletion.
     *
     * @param tasks   The task list to delete the task from.
     * @param ui      The UI to print the deletion message.
     * @param storage The storage to save the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RickyException {
        if (index > tasks.size() || index < 1) {
            throw new RickyException("The task index is out of range.");
        }
        String output = ui.getDeleteMessage(tasks.get(index - 1), tasks);
        tasks.delete(index - 1);
        return output;
    }
}

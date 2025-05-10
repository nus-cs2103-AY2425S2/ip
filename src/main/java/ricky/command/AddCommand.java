/**
 * Represents a command to add a task to the task list.
 */
package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;
import ricky.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list and printing the addition.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The UI to print the addition message.
     * @param storage The storage to save the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.checkDuplicate(task).size() > 0) {
            return ui.getDuplicateMessage(task);
        } else {
            tasks.add(task);
            return ui.getAddMessage(task, tasks);
        }
    }
}

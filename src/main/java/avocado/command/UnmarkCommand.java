package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */

public class UnmarkCommand extends Command {

    /** The task number to unmark. */
    private final int taskNumber;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param taskNumber The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Unmarks the task as not done and saves the updated task list to the storage file.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage object.
     * @throws AvocadoException If an error occurs while unmarking the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        boolean check = tasks.markTaskAsNotDone(taskNumber);
        if (!check) {
            throw new AvocadoException("There is no such task.");
        }
        storage.saveTasks(tasks.getTasks());
        return ui.showMarkedAsUndone(tasks.getTask(taskNumber));
    }
    
}

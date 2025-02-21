package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */

public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task as done and saves the updated task list to the storage file.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage object.
     * @throws AvocadoException If an error occurs while marking the task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        boolean check = tasks.markTaskAsDone(taskNumber);
        if (!check) {
            throw new AvocadoException("There is no such task.");
        }
        storage.saveTasks(tasks.getTasks());
        return ui.showMarkedAsDone(tasks.getTasks().get(taskNumber));
    }
}

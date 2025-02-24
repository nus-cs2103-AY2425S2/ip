package miki.command;

import miki.exception.DeleteFailedException;
import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Task;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskNumber Task number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.getSize()) { // check task number is within the range of available tasks
            throw new DeleteFailedException("The task you requested to delete does not exist.");
        }
        Task task = tasks.getTask(taskNumber - 1);
        assert task != null : "Task should not be null";

        tasks.deleteTask(taskNumber - 1);
        storage.save(tasks.getTaskList());
        String output = ui.showTaskDeleted(task, taskNumber);
        return output;
    }
}

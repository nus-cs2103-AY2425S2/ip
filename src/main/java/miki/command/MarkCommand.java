package miki.command;

import miki.exception.CheckException;
import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Task;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNumber The task number to mark as completed.
     */
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.getSize()) { // check task number is within the range of available tasks
            throw new CheckException("The task you requested to mark does not exist.");
        }
        Task task = tasks.getTask(taskNumber - 1);
        assert task != null : "Task should not be null";

        if (task.checkCompleted()) {
            throw new CheckException(
                    "Task " + taskNumber + " has already been completed.\n");
        }
        task.toggleCompletion();
        String output = ui.showTaskCompletion(task, taskNumber);
        storage.save(tasks.getTaskList());
        return output;
    }
}

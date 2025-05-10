package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the delete command.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the delete command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.deleteTask(taskId);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the task id of the task that is deleted.
     *
     * @return The task id of the task that is deleted.
     */
    public int getTaskId() {
        return taskId;
    }
}

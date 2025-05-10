package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the mark command.
 */
public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the mark command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.markTask(taskId);
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
     * Returns the task id of task that is marked.
     *
     * @return The task id of task that is marked.
     */
    public int getTaskId() {
        return taskId;
    }
}

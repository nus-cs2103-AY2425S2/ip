package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the tag command.
 */
public class UntagCommand extends Command {
    private final int taskId;
    private final String tagName;

    public UntagCommand(int taskId, String tagName) {
        this.taskId = taskId;
        this.tagName = tagName;
    }

    /**
     * Executes the untag command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.untagTask(taskId, tagName);
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

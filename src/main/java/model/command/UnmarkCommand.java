package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Task;
import model.task.TaskList;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param oneBasedIndex The one-based index of the task to unmark.
     */
    public UnmarkCommand(int oneBasedIndex) {
        this.index = oneBasedIndex - 1;
    }

    /**
     * Executes the unmark command, unmarking the task and updating the storage.
     *
     * @param tasks The list of tasks.
     * @param storage The storage component.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        Task task = tasks.getTask(index);
        task.unmark();
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_ADDED, task.toString());
    }
}

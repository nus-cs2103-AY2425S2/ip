package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Task;
import model.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param oneBasedIndex The one-based index of the task to delete.
     */
    public DeleteCommand(int oneBasedIndex) {
        this.index = oneBasedIndex - 1;
    }

    /**
     * Executes the delete command, removing the task and updating the storage.
     *
     * @param tasks The list of tasks.
     * @param storage The storage component.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_DELETED, task.toString());
    }
}

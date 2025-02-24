package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Task;
import model.task.TaskList;

public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param oneBasedIndex The one-based index of the task to mark.
     */
    public MarkCommand(int oneBasedIndex) {
        this.index = oneBasedIndex - 1;
    }

    /**
     * Executes the mark command.
     *
     * @param tasks The list of tasks.
     *
     * @param storage The storage.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        Task task = tasks.getTask(index);
        task.mark();
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_MARKED, task.toString());
    }
}

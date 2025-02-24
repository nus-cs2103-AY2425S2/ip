package model.command;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Task;
import model.task.TaskList;
import model.task.Todo;

/**
 * Represents a command to add a new todo task.
 */
public class TodoCommand extends Command {

    private final String name;

    /**
     * Constructs a TodoCommand with the specified task name.
     *
     * @param name The name of the todo task.
     */
    public TodoCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the todo command, adding the task and updating the storage.
     *
     * @param tasks The list of tasks.
     * @param storage The storage component.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        Task task = new Todo(name);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_ADDED, task.toString());
    }
}

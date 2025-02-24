package model.command;

import java.time.LocalDateTime;

import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Deadline;
import model.task.TaskList;

public class DeadlineCommand extends Command {

    private final String name;
    private final LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified name and deadline.
     *
     * @param name The name of the deadline.
     * @param by The deadline date and time.
     */
    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Executes the deadline command.
     *
     * @param tasks The list of tasks.
     * @param storage The storage.
     * @return The response to the user.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        Deadline deadline = new Deadline(name, by);
        tasks.addTask(deadline);
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_ADDED, deadline.toString());
    }

}

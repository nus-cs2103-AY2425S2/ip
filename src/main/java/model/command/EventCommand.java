package model.command;

import java.time.LocalDateTime;

import model.exception.AliceException;
import model.exception.EventChronologyException;
import model.response.Response;
import model.storage.Storage;
import model.task.Event;
import model.task.TaskList;

public class EventCommand extends Command {

    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventCommand with the specified name, start time, and end
     * time.
     *
     * @param name The name of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command.
     *
     * @param tasks The list of tasks.
     * @param storage The storage.
     * @return The response to the user.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        if (from.isAfter(to)) {
            throw new EventChronologyException();
        }
        Event event = new Event(name, from, to);
        tasks.addTask(event);
        storage.saveTasks(tasks);
        return new Response(Response.RESPONSE_TYPE.TASK_ADDED, event.toString());
    }

}

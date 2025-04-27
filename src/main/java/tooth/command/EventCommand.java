package tooth.command;

import java.time.LocalDate;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;
import tooth.task.Event;

/**
 * Command that creates a new event
 */
public class EventCommand implements Command {

    private String description;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor of EventCommand
     * @param description the description of the Event
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Execute Task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int prevNumTask = tasks.numTask();
        Event event = Event.of(description, from, to);
        tasks.add(event);
        ui.say("Added new event");
        assert prevNumTask < tasks.numTask();
    }
}

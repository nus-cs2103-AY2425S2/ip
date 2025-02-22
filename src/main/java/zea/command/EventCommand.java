package zea.command;

import java.time.LocalDateTime;

import zea.Storage;
import zea.ZeaException;
import zea.task.Event;
import zea.task.Tasks;
import zea.ui.Ui;

/**
 * A command for creating Events
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for creating Events
     * @param description - A description of the event
     * @param from - The start datetime of the event
     * @param to - The end datetime of the event
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        Event e = new Event(this.description, this.from, this.to);
        tasks.addTask(e);
        storage.save(tasks);
        return Ui.formatItem("Great! I've added a new event for you:", e,
                "You now have " + tasks.getTotalTasks() + " task(s)");
    }
}

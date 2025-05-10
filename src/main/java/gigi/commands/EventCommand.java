package gigi.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Events;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to create and add an event task.
 * */

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String description;
    private final LocalDateTime endTime;
    private final LocalDateTime startTime;

    /**
     * Constructs an EventCommand with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        Events event = new Events(description, startTime, endTime);
        tasks.addTask(event);

        tasks.saveTasks(storage);

        return ui.showAddMessage() + "\n"
                + ui.showMessage(String.valueOf(event)) + "\n"
                + ui.showTaskNumber(tasks);
    }
}

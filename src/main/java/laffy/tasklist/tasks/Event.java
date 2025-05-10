package laffy.tasklist.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import laffy.tasklist.TaskDateProvider;


/**
 * Represents a task with a start and end time, i.e. an event
 * in the task list.
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param desc The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event.
     *
     * @param desc The description of the event.
     * @param isDone The status of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String desc, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(desc, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString()
                + " (from: " + TaskDateProvider.formatDateTime(this.from)
                + " to: " + TaskDateProvider.formatDateTime(this.to) + ")";
    }

    @Override
    public ArrayList<String> toTaskData() {
        ArrayList<String> taskData = super.toTaskData();
        taskData.set(0, TYPE);
        taskData.add(TaskDateProvider.formatForStorage(this.from));
        taskData.add(TaskDateProvider.formatForStorage(this.to));
        return taskData;
    }

    @Override
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(this.from);
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.from;
    }
}

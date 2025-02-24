package model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Event task with the specified name, start time, and end
     * time.
     *
     * @param name the name of the task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the specified name, start time, end
     * time, and marked status.
     *
     * @param name the name of the task
     * @param from the start time of the event
     * @param to the end time of the event
     * @param isMarked the marked status of the task
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isMarked) {
        super(name, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(FORMATTER) + " to: " + to.format(FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the event task in a format suitable
     * for data storage.
     *
     * @return a string representation of the event task for data storage
     */
    @Override
    public String toDataString() {
        return "E|" + (isMarked ? "1" : "0") + "|" + name + "|" + from.format(FORMATTER) + "|" + to.format(FORMATTER);
    }

    /**
     * Compares this event to the specified object. The result is true if and
     * only if the argument is not null and is an Event object that has the same
     * name, marked status, start time, and end time as this event.
     *
     * @param obj the object to compare this event against
     * @return true if the given object represents an Event equivalent to this
     * event, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            return super.equals(obj) && this.from.equals(event.from) && this.to.equals(event.to);
        }
        return false;
    }
}

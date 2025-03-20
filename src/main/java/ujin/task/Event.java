package ujin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. An {@link Event} is a {@link Task} that has a description
 * and a start and end time to define when the event takes place. Both times are represented
 * as {@link LocalDateTime} objects.
 *
 * <p>This class provides methods to retrieve the start and end times and display the task
 * in a user-friendly string format with the event's time range included.</p>
 */
public class Event extends Task {

    /**
     * The {@link LocalDateTime} object representing the start time of the event.
     */
    protected LocalDateTime start;

    /**
     * The {@link LocalDateTime} object representing the end time of the event.
     */
    protected LocalDateTime end;

    /**
     * Constructs an {@link Event} task with the given description, start time, and end time.
     * The start and end times are parsed from the provided strings using the superclass's
     * {@link Task#parse(String)} method.
     *
     * @param description The description of the event task.
     * @param start The start time of the event as a string in the format "yyyy-MM-dd HH:mm".
     * @param end The end time of the event as a string in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = super.parse(start);
        this.end = super.parse(end);
    }

    /**
     * Returns the start time of this {@link Event} task.
     *
     * @return A {@link LocalDateTime} representing the start time.
     */
    public LocalDateTime start() {
        return start;
    }

    /**
     * Returns the end time of this {@link Event} task.
     *
     * @return A {@link LocalDateTime} representing the end time.
     */
    public LocalDateTime end() {
        return end;
    }

    /**
     * Returns a string representation of the {@link Event} task in the format:
     * [E] Description (from: yyyy-MM-dd HH:mm to: yyyy-MM-dd HH:mm)
     * This includes the task type, description, and the event's start and end times formatted
     * using the {@link DateTimeFormatter}.
     *
     * @return A string representing the {@link Event} task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + super.formatTheString(start, end);
    }
}

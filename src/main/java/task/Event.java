package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a new event task with the specified description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Creates a new event task with the specified description, start time, end time and completion status.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     * @param isDone The completion status of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the start time of the event task.
     *
     * @return The start time of the event task.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event task.
     *
     * @return The end time of the event task.
     */
    public LocalDateTime getTo() {
        return to;
    }
}


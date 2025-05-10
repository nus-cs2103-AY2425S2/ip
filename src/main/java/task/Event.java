package task;

import java.time.LocalDateTime;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs an Event task.
     * @param description The event description.
     * @param startTime The event start time in yyyy-MM-dd HHmm format.
     * @param endTime The event end time in yyyy-MM-dd HHmm format.
     * @param isDone Whether the event has already happened.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = parseDate(startTime);
        this.endTime = parseDate(endTime);

        if (this.endTime == null || this.startTime == null) {
            throw new IllegalArgumentException(" Invalid date format.");
        }
    }

    /**
     * Constructs an Event task that is initially not completed.
     * @param description The event description.
     * @param startTime The event start time in yyyy-MM-dd HHmm format.
     * @param endTime The event end time in yyyy-MM-dd HHmm format.
     */
    public Event(String description, String startTime, String endTime) {
        this(description, startTime, endTime, false);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: " + startTime.format(OUTPUT_FORMAT)
                + " to: " + endTime.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | "
                + (isDone ? "1" : "0")
                + " | " + description
                + " | " + startTime.format(INPUT_FORMAT)
                + " | " + endTime.format(INPUT_FORMAT);
    }
}

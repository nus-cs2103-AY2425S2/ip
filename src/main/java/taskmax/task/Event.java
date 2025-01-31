package taskmax.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startString The start date/time in "yyyy-MM-dd HHmm" format.
     * @param endString   The end date/time in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String startString, String endString) {
        super(description, TaskType.EVENT);
        this.start = LocalDateTime.parse(startString, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(endString, INPUT_FORMATTER);
    }

    /**
     * Retrieves the start date/time of the event.
     *
     * @return The start date/time as a LocalDateTime object.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Retrieves the end date/time of the event.
     *
     * @return The end date/time as a LocalDateTime object.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A formatted string displaying the task type, description, start, and end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMATTER)
                + " to: " + end.format(OUTPUT_FORMATTER) + ")";
    }
}

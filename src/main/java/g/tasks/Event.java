package g.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end date.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param startDate The start date of the event in `yyyy-MM-dd` format.
     * @param endDate The end date of the event in `yyyy-MM-dd` format.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDate.parse(startDate.trim());
        this.endDate = LocalDate.parse(endDate.trim());
    }

    /**
     * Constructs an Event task with completion status.
     *
     * @param description The description of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     * @param isDone Indicates whether the event has been completed.
     */
    public Event(String description, String startDate, String endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = LocalDate.parse(startDate.trim());
        this.endDate = LocalDate.parse(endDate.trim());
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + 
               " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + 
               " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startDate + " | " + endDate;
    }
}

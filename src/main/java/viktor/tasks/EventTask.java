package viktor.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import viktor.parser.DateParser;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class EventTask extends Task {
    private final String type;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a Event task with a description, start and end date.
     *
     * @param description The description of the task.
     * @param from The start date of the task in string format.
     * @param to The end date of the task in string format.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.type = "E";
        this.from = DateParser.parseDateTime(from);;
        this.to = DateParser.parseDateTime(to);;
    }

    /**
     * Returns the type of the task.
     *
     * @return The task type as a string.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Checks if the event matches the given date.
     *
     * @param targetTime The date to check against the event's date range.
     * @return true if the event is on the given date, false otherwise.
     */
    public boolean matchesDate(LocalDate targetTime) {
        LocalDate targetDate = targetTime;
        return !targetDate.isBefore(from.toLocalDate()) && !targetDate.isAfter(to.toLocalDate());
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + super.toString()
            + " (from: " + DateParser.formatDateTime(from) + " to: " + DateParser.formatDateTime(to) + ")";
    }

    /**
     * Returns the string format for saving the event task.
     *
     * @return The formatted string for saving the task.
     */
    @Override
    public String toSave() {
        return getType() + " | " + getStatusIcon() + " | " + description + " | "
            + DateParser.formatDateTime(from) + " | " + DateParser.formatDateTime(to);
    }
}

package eve.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a LocalDateTime start and end time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Initialize event with description as well as start and end times.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getDateTime() {
        return from;
    }

    @Override
    public String toDataString() {
        return "E | " + super.getStatusIcon() + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}

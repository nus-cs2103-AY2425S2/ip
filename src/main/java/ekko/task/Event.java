package ekko.task;

import java.time.LocalDateTime;

/**
 * Events are Todos with start and end time.
 */
public class Event extends Todo {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Create an event.
     * @param description String description
     * @param start starting date and time of the event
     * @param end ending date and time of the event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("The start time must be before the end time.");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Convert the event to its String representation.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E] %s %s (from: %s to: %s)",
            super.printDone(), super.printDes(), this.start.toString(), this.end.toString());
    }

    @Override
    public String toStore() {
        return "EVENT//" + super.storeDone() + "//" + super.printDes() + "//"
                + this.start.toString() + "//" + this.end.toString();
    }
}

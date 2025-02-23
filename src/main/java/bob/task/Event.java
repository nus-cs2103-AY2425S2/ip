package bob.task;

import java.time.LocalDateTime;

import bob.util.Helper;

/**
 * This class represents an event task (inherits from Task).
 * An event task has a description, a 'from' datetime and a 'to' datetime.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event object with the given description, 'from' datetime and 'to' datetime.
     *
     * @param description String.
     * @param from dateTime object.
     * @param to dateTime object.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event object with the given description, 'from' datetime, 'to' datetime and completed status.
     *
     * @param description String.
     * @param from dateTime object.
     * @param to dateTime object.
     * @param completed Boolean.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean completed) {
        super(description, completed);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String fromReadable = Helper.datetimeToReadable(this.from);
        String toReadable = Helper.datetimeToReadable(this.to);
        return "[E]" + super.toString() + " (from: " + fromReadable + " to: " + toReadable + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toCsv() {
        return String.format("E,%s,%b,,%s,%s", this.description, this.isCompleted, this.from, this.to);
    }
}

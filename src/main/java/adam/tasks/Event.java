package adam.tasks;

import java.time.LocalDate;

import adam.exceptions.AdamException;
import adam.parser.Parser;

/**
 * Represents a task that occurs over a period of time.
 */
public class Event extends Task {
    /** The start date of the event. */
    private LocalDate from;
    /** The end date of the event. */
    private LocalDate to;

    /**
     * Creates an Event with a description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws AdamException If the description is empty.
     */
    public Event(String description, LocalDate from, LocalDate to) throws AdamException {
        super(description);
        this.from = from;
        this.to = to;
        assert(!this.from.isAfter(this.to)) : "Start date should be before or equal to end date";
    }

    /**
     * Gets the event as a String.
     *
     * @return The event as a String.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.formatOutputDate(this.from), Parser.formatOutputDate(this.to));
    }

    /**
     * Gets the event as a String for logging.
     */
    @Override
    public String toLogString() {
        // Format back into the input format
        return String.format("E | %s | %s | %s", super.toLogString(),
                Parser.formatLogDate(this.from), Parser.formatLogDate(this.to));
    }

    /**
     * Checks if the event occurs on the specified date.
     *
     * @param date The date to check against.
     * @return True if the event occurs on the specified date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        // this.from <= date <= this.to
        return (this.from.isBefore(date) || this.from.equals(date))
                && (this.to.isAfter(date) || this.to.equals(date));
    }
}

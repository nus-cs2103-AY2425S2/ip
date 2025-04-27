package Ninon.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start date and an end date.
 * Inherits from the {@code Task} class.
 */
public class Event extends Task {
    protected LocalDate from; // Start date of the event.
    protected LocalDate to;   // End date of the event.

    /**
     * Constructs an Event with a description, start date, and end date.
     * The dates are parsed from string format to {@code LocalDate}.
     *
     * @param description the description of the event
     * @param from        the start date of the event in "yyyy-MM-dd" format
     * @param to          the end date of the event in "yyyy-MM-dd" format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a string representation of the event, including its status,
     * description, and formatted start and end dates.
     *
     * @return a formatted string representing the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() +
                " (from: " + from.format(formatter) +
                " to: " + to.format(formatter) + ")";
    }

    /**
     * Formats the event's data for output, following the format:
     * "E / [completion status] / description / start date / end date".
     *
     * @return a formatted string suitable for saving event details
     */
    @Override
    public String formatOut() {
        return "E / " + super.formatOut() + " / " + this.from + " / " + this.to;
    }
}
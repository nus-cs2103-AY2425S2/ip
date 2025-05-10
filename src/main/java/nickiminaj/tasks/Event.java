package nickiminaj.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents an event task with a start and end date-time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description and time period.
     *
     * @param description The description of the event.
     * @param from The start time of the event in string format (d/MM/yyyy HHmm).
     * @param to The end time of the event in string format (d/MM/yyyy HHmm).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Constructs an Event task with the specified description, time period, and completion status.
     *
     * @param description The description of the event.
     * @param from The start time of the event as a LocalDateTime object.
     * @param to The end time of the event as a LocalDateTime object.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string in the format dd/MM/yyyy HHmm.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        assert dateTime != null && !dateTime.trim().isEmpty() : "Error: DateTime string cannot be null or empty.";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Serializes the event task into a string format suitable for storage.
     *
     * @return A formatted string representing the event task for storage.
     */
    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);

        assert formattedFrom != null && !formattedFrom.trim().isEmpty() : "Error: Serialized start date must not be empty.";
        assert formattedTo != null && !formattedTo.trim().isEmpty() : "Error: Serialized end date must not be empty.";

        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");

        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);

        assert formattedFrom != null && !formattedFrom.trim().isEmpty() : "Error: Formatted start date must not be empty.";
        assert formattedTo != null && !formattedTo.trim().isEmpty() : "Error: Formatted end date must not be empty.";

        return "[E][" + (isDone ? "✓" : "✗") + "] " + description
                + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    /**
     * Checks if the event occurs on a specific date.
     *
     * @param date The date to check.
     * @return True if the event is on the specified date, false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return this.from.toLocalDate().equals(date) || this.to.toLocalDate().equals(date);
    }
}
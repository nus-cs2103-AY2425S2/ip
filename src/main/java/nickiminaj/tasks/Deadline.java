package nickiminaj.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline in string format (d/M/yyyy HHmm).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Constructs a Deadline task with the specified description, deadline date, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline as a LocalDateTime object.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string in the format dd/MM/yyyy HHmm.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy, h:mma");
        String formattedDate = by.format(formatter);
        assert formattedDate != null && !formattedDate.trim().isEmpty() : "Error: Formatted date must not be empty.";
        return "[D][" + (isDone ? "✓" : "✗") + "] " + description + " (by: " + by.format(formatter) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formattedDate = by.format(formatter);
        assert formattedDate != null && !formattedDate.trim().isEmpty() : "Error: Serialized date must not be empty.";
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Checks if the deadline is on a specific date.
     *
     * @param date The date to check.
     * @return True if the deadline is on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }
}

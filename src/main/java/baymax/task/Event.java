package baymax.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Baymax chatbot.
 * An event has a name, a start date/time, and an end date/time.
 * It supports input formats with or without time components.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
  
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a new {@code Event} task with the given name, start date/time, and end date/time.
     *
     * @param name The name of the event.
     * @param from The start date/time in the format {@code "yyyy-MM-dd"} or {@code "d/M/yyyy HHmm"}.
     * @param to The end date/time in the format {@code "yyyy-MM-dd"} or {@code "d/M/yyyy HHmm"}.
     */
    public Event(String name, String from, String to) {
        super(name);
        parseDates(from, to);
        assert (fromDate != null || toDate != null) : "Event start and end dates should not be null";
    }

    /**
     * Constructs a new {@code Event} task with the given name, start date/time, end date/time, and completion status.
     *
     * @param name The name of the event.
     * @param from The start date/time in the format {@code "yyyy-MM-dd"} or {@code "d/M/yyyy HHmm"}.
     * @param to The end date/time in the format {@code "yyyy-MM-dd"} or {@code "d/M/yyyy HHmm"}.
     * @param isCompleted {@code true} if the event is completed, {@code false} otherwise.
     */
    public Event(String name, String from, String to, Boolean isCompleted) {
        super(name, isCompleted);
        parseDates(from, to);
        assert (fromDate != null || toDate != null) : "Event start and end dates should not be null";
    }

    /**
     * Parses the given start and end dates/times and assigns them to the respective fields.
     * Supports both full {@code LocalDateTime} and {@code LocalDate} formats.
     * If an invalid format is provided, an error message is displayed.
     *
     * @param from The start date/time string.
     * @param to The end date/time string.
     */
    private void parseDates(String from, String to) {
        try {
            if (from.contains(" ")) {
                this.fromDateTime = LocalDateTime.parse(from, INPUT_FORMATTER_2);
            } else {
                this.fromDate = LocalDate.parse(from, INPUT_FORMATTER_1);
            }

            if (to.contains(" ")) {
                this.toDateTime = LocalDateTime.parse(to, INPUT_FORMATTER_2);
            } else {
                this.toDate = LocalDate.parse(to, INPUT_FORMATTER_1);
            }

            if (getFromAsDateTime().isAfter(getToAsDateTime())) {
                throw new IllegalArgumentException("Error: 'From' date must be before 'To' date.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid event format! Use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }
    /**
     * Returns the start date/time of the event.
     * If only a date was provided, it defaults to the start of the day (00:00).
     *
     * @return The start date/time as a {@code LocalDateTime}.
     */
    private LocalDateTime getFromAsDateTime() {
        return fromDateTime != null ? fromDateTime : (fromDate != null ? fromDate.atStartOfDay() : null);
    }

    /**
     * Returns the end date/time of the event.
     * If only a date was provided, it defaults to the start of the day (00:00).
     *
     * @return The end date/time as a {@code LocalDateTime}.
     */
    private LocalDateTime getToAsDateTime() {
        return toDateTime != null ? toDateTime : (toDate != null ? toDate.atStartOfDay() : null);
    }

    /**
     * Formats the given date/time for display purposes.
     *
     * @param dateTime The {@code LocalDateTime} to format.
     * @param date The {@code LocalDate} to format if {@code dateTime} is null.
     * @return A formatted string representing the date/time.
     */
    private String formatDateTime(LocalDateTime dateTime, LocalDate date) {
        if (dateTime != null) {
            return dateTime.format(DISPLAY_FORMATTER);
        }
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "Invalid date";
    }

    /**
     * Returns a string representation of the event task.
     * The format is: {@code [E]} followed by the task's status, name, start date/time, and end date/time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(fromDateTime, fromDate)
                + " to: " + formatDateTime(toDateTime, toDate) + ")";
    }

    /**
     * Returns a formatted string suitable for saving the event task to a file.
     * The format is: {@code E | isCompleted | Task Name | Start Date/Time | End Date/Time}.
     *
     * @return A formatted string representing the event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | "
                + (fromDateTime != null ? fromDateTime.format(INPUT_FORMATTER_2) : fromDate.format(INPUT_FORMATTER_1))
                + " | "
                + (toDateTime != null ? toDateTime.format(INPUT_FORMATTER_2) : toDate.format(INPUT_FORMATTER_1));
    }
}

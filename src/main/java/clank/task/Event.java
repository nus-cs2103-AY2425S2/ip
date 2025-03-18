package clank.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import clank.exception.ClankException;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.US);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a", Locale.US);

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time as a string.
     * @param end The end time as a string.
     * @throws ClankException If the date format is invalid.
     */
    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = parseDateTime(start);
            this.end = parseDateTime(end);
        } catch (DateTimeParseException e) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                    "event <description> /from d/M/yyyy HHmm /to <d/M/yyyy HHmm>");
        }
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param input The date-time string in the format "d/M/yyyy HHmm".
     * @return The parsed {@code LocalDateTime} object.
     * @throws DateTimeParseException If the input string is not in the expected format.
     */
    private LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, INPUT_FORMATTER);
    }

    /**
     * Converts the Event task to a format suitable for saving to a file.
     *
     * @return The formatted string for saving.
     */
    @Override
    public String toSaveFormat() {
        return "E|" + isDone + "|" + description + "|" + start.format(INPUT_FORMATTER)
                + "|" + end.format(INPUT_FORMATTER);
    }

    /**
     * Converts the Event task to a string representation.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMATTER)
                + " to: " + end.format(OUTPUT_FORMATTER) + ")";
    }
}

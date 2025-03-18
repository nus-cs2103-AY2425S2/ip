package clank.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import clank.exception.ClankException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.US);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a", Locale.US);

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with a description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string.
     * @throws ClankException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = parseDateTime(by);
        } catch (DateTimeParseException e) {
            throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                    "deadline <description> /by <d/M/yyyy HHmm>");
        }
    }

    /**
     * Retrieves the deadline date and time of the task.
     *
     * @return A {@code LocalDateTime} representing the deadline of the task.
     */
    public LocalDateTime getBy() {
        return by;
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
     * Converts the Deadline task to a format suitable for saving to a file.
     *
     * @return The formatted string for saving.
     */
    @Override
    public String toSaveFormat() {
        return "D|" + isDone + "|" + description + "|" + by.format(INPUT_FORMATTER);
    }

    /**
     * Converts the Deadline task to a string representation.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}

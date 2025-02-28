package gptzerofive.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gptzerofive.exception.InvalidDateFormatException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, String by) throws InvalidDateFormatException {
        super(description);
        this.by = parseDate(by);
    }

    /**
     * Constructs a new Deadline with the specified description.
     *
     * @param description The description of the deadline.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Parses a date string to LocalDateTime.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDateTime.
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    private LocalDateTime parseDate(String date) throws InvalidDateFormatException {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use d/M/yyyy HHmm.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String convertToFileString() {
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | "
                + by.format(INPUT_FORMATTER) + " | " + super.getNote();
    }

}

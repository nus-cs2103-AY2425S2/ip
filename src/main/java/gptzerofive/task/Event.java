package gptzerofive.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructs a new Event with the specified description, start date, and end
     * date.
     *
     * @param description The description of the event.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
    }

    public Event(String description) {
        super(description);
    }

    /**
     * Parses a date string to LocalDateTime.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDateTime.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use d/M/yyyy HHmm.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate.format(OUTPUT_FORMATTER) + " to "
                + endDate.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String convertToFileString() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | "
                + startDate.format(INPUT_FORMATTER) + " | " + endDate.format(INPUT_FORMATTER) + " | " + super.getNote();
    }
}

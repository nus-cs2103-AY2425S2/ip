package julie.task;

import julie.exception.WrongFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime by;
    private static final String MARKER = "[E]";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public Event(String description, String from, String by) throws WrongFormatException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);

            if (!this.by.isAfter(this.from)) {
                throw new WrongFormatException("The end date/time of an event must be after the start date/time!");
            }
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM");
        }

    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(INPUT_FORMATTER) + " | " + by.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return MARKER + " " + super.toString() + " (from: " +
                from.format(OUTPUT_FORMATTER) + " to: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}

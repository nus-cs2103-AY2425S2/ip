package julie.task;

import julie.exception.WrongFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final String MARKER = "[D]";

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public Deadline(String description, String by) throws WrongFormatException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM");
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return MARKER + " " + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}

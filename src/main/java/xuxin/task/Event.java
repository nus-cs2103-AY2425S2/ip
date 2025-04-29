package xuxin.task;

import xuxin.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    private static final String STATUM = "[E]";
    private LocalDate start;
    private LocalDate end;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy"); // e.g., 2/12/2019 1800
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH); // e.g., Dec 02 2019

    // Constructor for an event with start and end dates
    public Event(String name, String start, String end) throws DukeException {
        super(name, false); // Call superclass constructor
        try {
            this.start = LocalDate.parse(start, INPUT_FORMAT); // Parse the start date and time
            this.end = LocalDate.parse(end, INPUT_FORMAT); // Parse the end date and time
        } catch (Exception e) {
            throw new DukeException("Invalid date format! Please use d/M/yyyy (e.g., 2/12/2019).");
        }
    }

    // Constructor for an event with start and end dates and status
    public Event(String name, String start, String end, boolean isDone) throws DukeException {
        super(name, isDone);
        try {
            this.start = LocalDate.parse(start, INPUT_FORMAT); // Parse the start date and time
            this.end = LocalDate.parse(end, INPUT_FORMAT); // Parse the end date and time
        } catch (Exception e) {
            throw new DukeException("Invalid date format! Please use d/M/yyyy (e.g., 2/12/2019).");
        }
    }

    @Override
    public String toString() {
        // Format the start and end dates to user-friendly formats
        return STATUM + super.toString() +
                String.format(" (from: %s to: %s)",
                        this.start.format(OUTPUT_FORMAT), this.end.format(OUTPUT_FORMAT));
    }

    @Override
    public String toFileFormat() {
        // Save the task in the original input format for consistency
        return "E | " + (isDone ? "1" : "0") + " | " + this.name + " | " +
                this.start.format(INPUT_FORMAT) + " | " + this.end.format(INPUT_FORMAT);
    }

}

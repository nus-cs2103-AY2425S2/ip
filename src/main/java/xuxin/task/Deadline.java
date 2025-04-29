package xuxin.task;

import xuxin.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final String STATUM = "[D]";
    private LocalDate date;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy"); // e.g., 2/12/2019
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH); // e.g., Dec 02 2019, 6:00 PM

    /**
     * Constructs a Deadline task with a name and due date.
     *
     * @param name The name of the task.
     * @param date The due date in "dd/MM/yyyy" format.
     * @throws DukeException If the date format is incorrect.
     */
    public Deadline(String name, String date) throws DukeException {
        super(name, false); // Call superclass constructor
        try {
            this.date = LocalDate.parse(date, INPUT_FORMAT); // Parse the date string into LocalDateTime
        } catch (Exception e) {
            throw new DukeException("Invalid date format! Please use d/M/yyyy (e.g., 2/12/2019).");
        }
    }

    /**
     * Constructs a Deadline task with a specified due date and completion status.
     *
     * @param name The name of the deadline task.
     * @param date The due date in "d/M/yyyy" format.
     * @param isDone Indicates whether the task is completed.
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String name, String date, boolean isDone) throws DukeException {
        super(name, isDone);
        try {
            this.date = LocalDate.parse(date, INPUT_FORMAT); // Parse the date string into LocalDateTime
        } catch (Exception e) {
            throw new DukeException("Invalid date format! Please use d/M/yyyy (e.g., 2/12/2019).");
        }
    }

    @Override
    public String toString() {
        // Format the date into a readable format
        return STATUM + super.toString() + String.format(" (by: %s)", this.date.format(OUTPUT_FORMAT));
    }

    @Override
    public String toFileFormat() {
        // Save the task in the original input format for consistency
        return "D | " + (isDone ? "1" : "0") + " | " + this.name + " | " + this.date.format(INPUT_FORMAT);
    }
}

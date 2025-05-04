package softess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task includes a description, a due date, and a completion status.
 * The due date is stored as a string but can be formatted using {@code LocalDateTime}.
 *
 * Supported date format for input: {@code yyyy-MM-dd HHmm} (e.g., 2024-02-03 1800).
 * The output format is displayed as: {@code MMM dd yyyy, h:mm a} (e.g., Feb 03 2024, 6:00 PM).
 *
 * @author Hrishikesh Sathyian
 */
public class Deadline extends Task {

    /** The deadline date as a string */
    protected String by;

    /** The expected input format for the deadline */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** The output format for displaying the deadline */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a new {@code Deadline} task.
     *
     * @param description The task description.
     * @param by The deadline date as a string in {@code yyyy-MM-dd HHmm} format.
     * @param isDone The status of the task; {@code true} if completed, {@code false} otherwise.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        super.isDone = isDone;

        // Try parsing the input date, if invalid, update `by` with the current date
        try {
            LocalDateTime.parse(by.trim(), INPUT_FORMAT); // Validate format
            this.by = by.trim();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format detected. Setting deadline to today's date.");
            this.by = LocalDateTime.now().format(INPUT_FORMAT); // Correcting to today's date in proper format
        }
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return A formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        String byFormatted;
        try {
            byFormatted = LocalDateTime.parse(this.by, INPUT_FORMAT).format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            // This should not happen, but in case it does, default to today's date
            byFormatted = LocalDateTime.now().format(OUTPUT_FORMAT);
        }
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }

    /**
     * Marks this task as completed and prints a confirmation message.
     */
    @Override
    public String markAsDone() {
        super.isDone = true;
        return "Nice! I've marked this task as done: \n " + this.toString();
    }

    /**
     * Marks this task as not completed and prints a confirmation message.
     */
    @Override
    public String markAsUnDone() {
        super.isDone = false;
        return "Nice! I've marked this task as undone: \n " + this.toString();
    }

    /**
     * Generates a formatted string representation of the deadline task for file storage.
     * The format follows: {@code DEADLINE|status|description|deadline}.
     *
     * @return A string representation of the deadline suitable for file storage.
     */
    @Override
    public String generateTextToFile() {
        int status = this.isDone ? 1 : 0;
        return "DEADLINE|%d|%s|%s".formatted(status, this.description, this.by);
    }
}

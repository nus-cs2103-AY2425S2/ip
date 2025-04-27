package Ninon.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the {@code Task} class.
 */
public class Deadline extends Task {
    protected LocalDate by; // The due date of the deadline task.

    /**
     * Constructs a Deadline task with a description and a due date.
     * The due date is parsed from a string to a {@code LocalDate} object.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the task in "yyyy-MM-dd" format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the deadline task,
     * including its status, description, and formatted due date.
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Formats the deadline task for output, following the format:
     * "D / [completion status] / description / due date".
     *
     * @return a formatted string suitable for saving deadline task details
     */
    @Override
    public String formatOut() {
        return "D / " + super.formatOut() + " / " + this.by;
    }
}
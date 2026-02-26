package carolyn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline task has a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with a given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Formats the due date as "MMM dd yyyy" (e.g., "Jan 01 2025").
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}

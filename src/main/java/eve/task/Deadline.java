package eve.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a LocalDateTime due date.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Initialize a deadline with description and due date.
     *
     * @param description Description of task.
     * @param by Due date of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getDateTime() {
        return by;
    }

    @Override
    public String toDataString() {
        return "D | " + super.getStatusIcon() + " | " + this.description
                + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }
}

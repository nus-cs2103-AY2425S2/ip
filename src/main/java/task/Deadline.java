package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Creates a new deadline task with the specified description, deadline and completion status.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     * @param isDone The completion status of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return The deadline of the deadline task.
     */
    public LocalDateTime getBy() {
        return by;
    }
}

package adam.tasks;

import java.time.LocalDate;

import adam.exceptions.AdamException;
import adam.parser.Parser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private final LocalDate deadline;

    /**
     * Constructs a Deadline object with a specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline date for the task.
     * @throws AdamException If the description is empty or invalid.
     */
    public Deadline(String description, LocalDate deadline) throws AdamException {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task as a String.
     *
     * @return The deadline of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatOutputDate(this.deadline));
    }

    /**
     * Gets the deadline of the task as a String for logging.
     */
    @Override
    public String toLogString() {
        // Format back into the input format
        return String.format("D | %s | %s", super.toLogString(),
                Parser.formatLogDate(this.deadline));
    }

    /**
     * Checks if the task is due on the specified date.
     *
     * @param date The date to check against.
     * @return True if the task is due on the specified date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        return this.deadline.equals(date);
    }
}

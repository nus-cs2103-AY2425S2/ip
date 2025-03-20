package ujin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A {@link Deadline} is a {@link Task} that has
 * a description and a specific deadline time by which the task must be completed.
 * The deadline is represented as a {@link LocalDateTime} object.
 *
 * <p>This class provides methods to retrieve the deadline and display the task
 * in a user-friendly string format with the deadline information included.</p>
 */
public class Deadline extends Task {

    /**
     * The {@link LocalDateTime} object representing the deadline by which the task
     * must be completed.
     */
    protected LocalDateTime by;

    /**
     * Constructs a {@link Deadline} task with the given description and deadline time.
     * The deadline time is parsed from the provided string using the superclass's
     * {@link Task#parse(String)} method.
     *
     * @param description The description of the task.
     * @param by The deadline time as a string in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = super.parse(by);
    }

    /**
     * Returns the deadline time of this {@link Deadline} task.
     *
     * @return A {@link LocalDateTime} representing the deadline.
     */
    public LocalDateTime by() {
        return by;
    }

    /**
     * Returns a string representation of the {@link Deadline} task in the format:
     * [D] Description (by: yyyy-MM-dd HH:mm)
     * This includes the task type, description, and the deadline formatted using
     * the {@link DateTimeFormatter}.
     *
     * @return A string representing the {@link Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + super.formatTheString(by);
    }
}

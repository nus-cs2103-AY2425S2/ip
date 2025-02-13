package zoe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code Deadline} is a type of {@link Task} that includes a specific date by which
 * the task should be completed. The date is stored as a {@link LocalDate} and is formatted
 * in a human-readable form when the task description is requested.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Deadline deadline = new Deadline("Submit report", LocalDate.of(2025, 2, 15));
 *     System.out.println(deadline.getDescription());
 * </pre>
 * </p>
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a {@code Deadline} with the specified task name and deadline date.
     *
     * @param taskName the name or description of the task
     * @param by the {@link LocalDate} representing the deadline
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the type icon for a deadline task.
     * <p>
     * This method is used to identify the type of task in the user interface.
     * </p>
     *
     * @return a string "D" representing the deadline type icon
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a detailed description of the deadline task.
     * <p>
     * The description includes the task name along with its deadline, formatted as
     * "MMM d yyyy" (e.g., "Feb 15 2025").
     * </p>
     *
     * @return a string describing the deadline task with its deadline date
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("%s (by: %s)", taskName, by.format(formatter));
    }
}

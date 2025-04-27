package Ninon.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that should be done after a specified date or after another task is completed.
 * Inherits from the {@code Task} class.
 */
public class DoAfter extends Task {
    protected LocalDate by; // The date after which the task should be done.
    protected Task afterTask; // The task that must be completed before this task.

    /**
     * Constructs a DoAfter task with a description and a date.
     * The task should be done after the specified date.
     *
     * @param description the description of the task
     * @param by          the date after which the task should be done in "yyyy-MM-dd" format
     */
    public DoAfter(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs a DoAfter task with a description and another task.
     * The task should be done after the specified task is completed.
     *
     * @param description the description of the task
     * @param task        the task that must be completed before this one
     */
    public DoAfter(String description, Task task) {
        super(description);
        this.afterTask = task;
    }

    /**
     * Returns a string representation of the DoAfter task,
     * including its status, description, and the condition for when it should be done.
     *
     * @return a formatted string representing the DoAfter task
     */
    @Override
    public String toString() {
        if (this.afterTask != null) {
            return "[A]" + super.toString() + " (by: " + this.afterTask.toString() + ")";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[A]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Formats the DoAfter task for output, following the format:
     * "A / [completion status] / description / (task or date after which it should be done)".
     *
     * @return a formatted string suitable for saving DoAfter task details
     */
    @Override
    public String formatOut() {
        if (this.afterTask != null) {
            return "A / " + super.formatOut() + " / " + this.afterTask.formatOut();
        }
        return "A / " + super.formatOut() + " / " + this.by;
    }
}

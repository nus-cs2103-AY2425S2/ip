package ziyang;

import java.time.LocalDate;

/**
 * Represents a task with a deadline date.
 * Extends the base Task class.
 */
public class deadlineTask extends Task {
    /** The deadline date for the task */
    public LocalDate deadline;

    /**
     * Constructs a new deadline task.
     * @param description The description of the task
     * @param deadline The deadline date in ISO format (yyyy-MM-dd)
     */
    public deadlineTask(String description, String deadline) {
        super(description);
        LocalDate date = LocalDate.parse(deadline);
        this.deadline = date;
    }

    /**
     * Returns a string representation of the deadline task.
     * @return A string in the format "[D][status]description(by: deadline)"
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + "(by: " + this.deadline + ")";
    }
}

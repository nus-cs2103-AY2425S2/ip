package viktor.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import viktor.parser.DateParser;

/**
 * Represents an Deadline task with a description, and due time
 */
public class DeadlineTask extends Task {
    private final String type;
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in string format.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.type = "D";
        this.by = DateParser.parseDateTime(by);;
    }

    /**
     * Returns the type of the task.
     *
     * @return The task type as a string.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Checks if the task matches the given date.
     *
     * @param targetDate The target date to compare against.
     * @return True if the task's due date matches the target date, false otherwise.
     */
    public boolean matchesDate(LocalDate targetDate) {
        return targetDate.equals(by.toLocalDate());
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + super.toString()
            + " (by: " + DateParser.formatDateTime(by) + ")";
    }

    /**
     * Returns the string format for saving the Deadline task.
     *
     * @return The formatted string for saving the task.
     */
    @Override
    public String toSave() {
        return getType() + " | " + getStatusIcon() + " | " + description + " | " + DateParser.formatDateTime(by);
    }
}

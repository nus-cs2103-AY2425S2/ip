package buddy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import buddy.command.Command;
import buddy.exception.BuddyException;

/**
 * Represents Deadline event
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Deadline task description.
     * @param dueDate     String representation of the deadline.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.by = dueDate;
    }

    @Override
    public String toStorageStringFormat() {
        String result = "D | ";
        result += this.isDone ? "1" : "0";
        result += " | " + this.description + " | ";
        result += this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
        return result;
    }

    @Override
    public void updateTask(String field, String newValue) throws BuddyException {
        switch (field) {
        case "/by" -> this.by = Command.getDateAndTime(newValue);
        case "/description" -> this.description = newValue;
        default -> throw new BuddyException("Invalid update field for Event! Use '/by', '/description'.");
        }
    }

    /**
     * Retrieves string representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(Task.PATTERN_WRITE)
                + ")";
    }
}

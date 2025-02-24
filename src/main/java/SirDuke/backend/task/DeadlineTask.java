package SirDuke.backend.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. A <code>DeadlineTask<code> object has a
 * <code>description<code> String toBeCompletedBy that is the time that the task must be completed by.
 */
public class DeadlineTask extends Task {

    protected LocalDate toBeCompletedBy;

    /**
     * Constructor for an Event object
     *
     * @param description name of the task
     * @param toBeCompletedBy the time that the task must be completed by
     *
     * @throws DateTimeParseException if string is in an invalid format
     */
    public DeadlineTask(String description, String toBeCompletedBy)
            throws DateTimeParseException {
        super(description);
        this.toBeCompletedBy = LocalDate.parse(toBeCompletedBy);
    }

    @Override
    public String toFileEntry() {
        return "D|" + getStatusIcon() + "|" + description + "|" + toBeCompletedBy;
    }

    @Override
    public String getTaskType() {
        return "DEADLINE";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + toBeCompletedBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

package SirDuke.backend.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. A <code>DeadlineTask<code> object has a
 * <code>description<code> String toBeCompletedBy that is the time that the task must be completed by.
 */
public class DeadlineTask extends Task {

    protected LocalDateTime toBeCompletedBy;


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
        this.toBeCompletedBy = LocalDateTime.parse(toBeCompletedBy, parsingFormatter);
    }

    public void setToBeCompletedBy(String newToBeCompletedBy) throws DateTimeParseException {
        this.toBeCompletedBy = LocalDateTime.parse(newToBeCompletedBy, parsingFormatter);
    }

    @Override
    public String toFileEntry() {
        return "D|" + getStatusIcon() + "|"
                + description + "|" + toBeCompletedBy.format(parsingFormatter);
    }

    @Override
    public String getTaskType() {
        return "DEADLINE";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + toBeCompletedBy.format(printingFormatter) + ")";
    }
}

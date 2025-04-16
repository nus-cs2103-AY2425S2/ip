package pluto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. It is a task
 * with a description and a specified end date
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new task, Deadline, where
     * users can specify an end date for the task
     * @param description a String describing the task
     * @param by a String that specifies the end date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Creates a new task, Deadline, where users can
     * specify an end date, and whether the task is done
     * @param description a String that describes the task
     * @param by a String that specifies the end date of task
     * @param isDone a boolean that indicates whether task is completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.taskStatusMessage() + " (by: " + date + ")";
    }

    /**
     * Converts task to file format to be stored in tasks file
     * @return a String to be stored in tasks file
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Checks if the Task is due on a specific day
     * @param dateInput the Date of the day
     * @return a boolean that indicates if the task
     * is due that day
     */
    @Override
    public boolean isScheduledFor(String dateInput) {
        LocalDate date = LocalDate.parse(dateInput);
        return date.isEqual(this.by);
    }
}

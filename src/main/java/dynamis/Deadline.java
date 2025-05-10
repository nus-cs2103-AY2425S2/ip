package dynamis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. Deadlines are in datetime format.
 */
public class Deadline extends Task{
    protected LocalDate dueBy;
    protected String dueByString;

    /**
     * Constructs a Deadline Object. A type of task for the tasklist.
     *
     * @param name The name or description of the task.
     * @param dueBy The due date of the task in yyyy-MM-dd format.
     */
    public Deadline(String name, String dueBy) {
        super(name);
        try {
            this.dueBy = LocalDate.parse(dueBy);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format :" + e.getMessage());
            System.out.println("Using literal string instead.");
            if (dueBy != null) {
                this.dueByString = dueBy;
            }
            this.dueBy = null;
        }
    }

    @Override
    public String toString() {
        if (dueBy != null) {
            return "[D]" + super.toString() + " (by: "
                    + dueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dueByString + ")";
        }
    }
}

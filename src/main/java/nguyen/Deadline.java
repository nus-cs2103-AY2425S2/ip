package nguyen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;
    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time.
     */
    public Deadline(String description, String by) throws NguyenException {
        super(description);
        this.by = DateParser.parseDate(by);
    }
    /**
     * Return the type of this task
     **/
    @Override
    public String getType() {
        return "deadline";
    }
    /**
     * Return the date
     **/
    @Override
    public LocalDate getDate() {
        return by;
    }
    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

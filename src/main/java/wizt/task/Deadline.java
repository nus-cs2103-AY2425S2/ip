package wizt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents the Deadline Task
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Represent a Constructor that takes in description and localdatetime
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * @return Deadline tasks in a specified format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}

package paimon.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of Todo that has a deadline.
 */
public class Deadline extends Todo {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     * 
     * @param description same as description in Todo
     * @param by the deadline of the task in format "d/M/yyyy HHmm"
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String getType() {
        return "[D]";
    } 

    @Override
    public String to_save() {
        return String.format("%s | %d | %s | %s",
            getType(),
            this.isDone ? 1 : 0,
            getDescription(),
            this.by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedBy = this.by.format(formatter);
        return super.toString() + " (by: " + formattedBy + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return super.equals(d) && this.by.equals(d.by);
        } else {
            return false;
        }
    }
}

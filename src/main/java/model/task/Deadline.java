package model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDateTime by, Boolean isMarked) {
        super(name, isMarked);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the deadline task in a format suitable
     * for data storage.
     *
     * @return a string representation of the deadline task for data storage
     */
    @Override
    public String toDataString() {
        return "D|" + (isMarked ? "1" : "0") + "|" + name + "|" + by.format(FORMATTER);
    }

    /**
     * Compares this deadline to the specified object. The result is true if and
     * only if the argument is not null and is a Deadline object that has the
     * same name, marked status, and due date as this deadline.
     *
     * @param obj the object to compare this deadline against
     * @return true if the given object represents a Deadline equivalent to this
     * deadline, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline deadline) {
            return super.equals(obj) && this.by.equals(deadline.by);
        }
        return false;
    }
}

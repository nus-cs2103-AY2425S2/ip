package bob.task;

import java.time.LocalDateTime;

import bob.util.Helper;

/**
 * This class represents a deadline task (inherits from Task).
 * A deadline task has a description and a 'by' datetime, indicating its due date.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline object with the given description and 'by' datetime.
     *
     * @param description String.
     * @param by dateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object with the given description, 'by' datetime and completed status.
     *
     * @param description String.
     * @param by dateTime object.
     * @param completed Boolean.
     */
    public Deadline(String description, LocalDateTime by, boolean completed) {
        super(description, completed);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String byReadable = Helper.datetimeToReadable(this.by);
        return "[D]" + super.toString() + " (" + "by: " + byReadable + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toCsv() {
        return String.format("D,%s,%b,%s,,", this.description, this.isCompleted, this.by);
    }
}

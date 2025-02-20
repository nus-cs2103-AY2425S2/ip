package nana.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * A deadline task is a task that needs to be done by a specific date.
 */
public class Deadline extends Task {

    private LocalDate by;
    private String byString;

    /**
     * Constructs a new Deadline instance with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param by the due date of the deadline task in YYYY-MM-DD format
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        this.by = LocalDate.parse(by);
        priority = Priority.MEDIUM;
    }

    /**
     * Constructs a new Deadline instance with the specified description, due date, and completion status.
     *
     * @param description the description of the deadline task
     * @param by the due date of the deadline task in YYYY-MM-DD format
     * @param isDone the completion status of the deadline task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byString = by;
        this.by = LocalDate.parse(by);
        priority = Priority.MEDIUM;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string representation includes the task type, task description, and due date.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the deadline task for storage.
     * The string representation includes the completion status, task type, task description, and due date.
     *
     * @return a string representation of the deadline task for storage
     */
    @Override
    public String toStorage() {
        return toStorageIsDone() + " D deadline " + description + " /by " + byString;
    }
}

package buddy.task;

import java.time.format.DateTimeFormatter;

import buddy.exception.BuddyException;

/**
 * Represents Task event
 */
public abstract class Task {
    public static final DateTimeFormatter PATTERN_STORE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter PATTERN_WRITE = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of the task status.
     *
     * @return string representation of the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTaskAsDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * To storage string format string.
     *
     * @return the string
     */
    public abstract String toStorageStringFormat();


    /**
     * Updates task.
     */
    public abstract void updateTask(String field, String newValue) throws BuddyException;

    /**
     * Returns string representation of a task.
     *
     * @return String representation of a task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

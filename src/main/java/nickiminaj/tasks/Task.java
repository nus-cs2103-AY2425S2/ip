package nickiminaj.tasks;

import java.time.LocalDate;

/**
 * Represents an abstract task with a description and completion status.
 * This class provides common functionality for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return "X" if the task is completed, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Serializes the task into a string format suitable for storage.
     *
     * @return A formatted string representing the task for storage.
     */
    public abstract String serialize();

    /**
     * Checks if the event or task occurs on the specified date.
     *
     * @param date The date to check.
     * @return {@code true} if the event or task falls on the specified date, {@code false} otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}

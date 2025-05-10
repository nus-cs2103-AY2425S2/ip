package ricky.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
    protected String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        this.description = input;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Returns a string representation of the task for storage.
     *
     * @return A string representation of the task for storage.
     */
    public String storeInfo() {
        return String.format(" | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }
}

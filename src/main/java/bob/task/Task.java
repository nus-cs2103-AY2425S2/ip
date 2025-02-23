package bob.task;

/**
 * This class represents a task object.
 * A task object has a description and a completed status.
 *
 * Subclasses should implement the toCsv method to convert the task to a
 * CSV string.
 */
public abstract class Task {
    protected boolean isCompleted;
    protected final String description;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description String.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructs a new Task object with the given description and completed status.
     *
     * @param description String.
     * @param completed Boolean.
     */
    public Task(String description, boolean completed) {
        this.description = description;
        this.isCompleted = completed;
    }

    /**
     * Converts the task to a CSV string.
     *
     * @return CSV-friendly String.
     */
    public abstract String toCsv();

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + description;
    }
}

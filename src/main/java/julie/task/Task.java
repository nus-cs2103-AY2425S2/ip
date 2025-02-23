package julie.task;

/**
 * Represents an abstract task that can be marked as done or undone.
 * This serves as a base class for specific task types such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;
    /**
     * Represents priority levels for tasks.
     */
    public enum Priority {
        H, M, L
    }
    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.L;
    }

    /**
     * Checks whether this task is equal to another object.
     * Subclasses must implement this method to define equality based on task type, description,
     * and other relevant attributes (e.g., due date for deadlines, start and end times for events).
     *
     * @param obj The object to compare with this task.
     * @return {@code true} if the specified object is equal to this task, {@code false} otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority level (H, M, L).
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Gets the priority level of the task.
     *
     * @return The priority level of the task.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     * "[X]" for completed tasks, "[ ]" for incomplete tasks.
     *
     * @return The status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getPriorityIcon() {
        return "[" + this.getPriority() + "]";
    }

    /**
     * Abstract method for subclasses to provide their own marker.
     *
     * @return The marker for the task type.
     */
    protected abstract String getMarker();

    /**
     * Returns the formatted string to be stored in a file.
     * Subclasses must implement this method to define their own file format.
     *
     * @return The formatted string representing the task for storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     * The format includes the status icon followed by the task description.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}

package nana.logic;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    protected Priority priority;
    /**
     * Constructs a new Task instance with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        priority = Priority.LOW;
    }

    /**
     * Constructs a new Task instance with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        priority = Priority.LOW;
    }

    /**
     * Returns the status icon of the task.
     * The status icon is "X" if the task is done, otherwise it is a space.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task for storage.
     * The string representation includes the completion status and description.
     *
     * @return a string representation of the task for storage
     */
    public String toStorage() {
        return toStorageIsDone() + " " + description;
    }

    /**
     * Returns the completion status of the task as a string.
     * The string is "true" if the task is done, otherwise it is "false".
     *
     * @return the completion status of the task as a string
     */
    public String toStorageIsDone() {
        return (isDone ? "true" : "false");
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the priority of the task.
     *
     * @return the priority of the task
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority the priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}

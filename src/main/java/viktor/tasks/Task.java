package viktor.tasks;

/**
 * Represents a task with a description and a status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void beDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void beUndone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task (to be implemented by subclasses).
     *
     * @return The task type as a string.
     */
    public abstract String getType();

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task based on whether it's done or not.
     *
     * @return "X" if the task is done, " " (empty space) if not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns the string format for saving the task (to be implemented by subclasses).
     *
     * @return The formatted string for saving the task.
     */
    public abstract String toSave();
}

package g.tasks;

/**
 * Represents an abstract task with a description and completion status.
 * This class is extended by specific task types like {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a given description.
     * The task is initially set as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Indicates whether the task is completed.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the task's status: "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isDone True if the task is completed, false otherwise.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * Retrieves the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if two tasks are considered equal based on their description.
     *
     * @param obj The object to compare with.
     * @return True if the descriptions are the same (case-insensitive), false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return description.equalsIgnoreCase(task.description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string containing the status icon and task description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the formatted string representation of the task for file storage.
     *
     * @return A string in a format suitable for saving the task to a file.
     */
    public abstract String toFileString();
}

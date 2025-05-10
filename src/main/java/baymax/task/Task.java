package baymax.task;

/**
 * Represents a task that can be managed by the Baymax chatbot.
 * A task has a name and a completion status.
 * This is an abstract class that serves as a base for different types of tasks.
 */
public abstract class Task {
    protected String name;
    protected boolean isCompleted;

    /**
     * Constructs a new {@code Task} with the specified name.
     * The task is initially marked as incomplete.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Constructs a new {@code Task} with the specified name and completion status.
     *
     * @param name        The name of the task.
     * @param isCompleted {@code true} if the task is completed, {@code false} otherwise.
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmarkAsCompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns the status icon of the task.
     * {@code "[X]"} represents a completed task, while {@code "[ ]"} represents an incomplete task.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns a string representation of the task, including its status and name.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.name;
    }

    /**
     * Returns a formatted string suitable for saving the task to a file.
     * The format is: {@code " | isCompleted | Task Name"}.
     *
     * @return A formatted string representing the task for file storage.
     */
    public String toFileString() {
        return " | " + isCompleted + " | " + this.name;
    }
}

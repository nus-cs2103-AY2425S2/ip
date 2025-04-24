package jimmy.tasks;

/**
 * Represents an abstract task with a name and completion status.
 * Subclasses should define specific task types like Todo, Deadline, etc.
 */
public abstract class Task {
    protected String name;
    protected boolean isCompleted;

    /**
     * Constructs a task with the specified name.
     *
     * @param name the name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return the task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is completed, otherwise {@code false}.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns the task's completion status as a symbol.
     *
     * @return "X" if completed, otherwise a blank space " ".
     */
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Converts the task to a file-friendly format for storage.
     *
     * @return the string representation of the task for file saving.
     */
    public abstract String toFileFormat();
}

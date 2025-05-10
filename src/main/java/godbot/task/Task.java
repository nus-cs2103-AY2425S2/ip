package godbot.task;

/**
 * Represents an abstract task in the task management system.
 * This class provides common functionality for specific types of tasks like {@link ToDo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return {@code "X"} if the task is done, otherwise a blank space {@code " "} if not done.
     */
    public String getStatus() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Marks the task as done by setting its status to {@code true}.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to {@code false}.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display purposes.
     *
     * @return A formatted string with the task's status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "]" + this.description;
    }

    /**
     * Converts the task to a file-friendly format for storage purposes.
     * This method must be implemented by subclasses.
     *
     * @return A string representing the task in a format suitable for saving to a file.
     */
    public abstract String toFileFormat();
}


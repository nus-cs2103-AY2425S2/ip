package perrygpt.tasks;

/**
 * The {@code Task} class represents a general task with a description,
 * a completion status, and a specific task type.
 * It serves as an abstract base class for specific task types such as {@link Todo},
 * {@link Deadline}, and {@link Event}.
 */

public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    protected TaskType tasktype;

    /**
     * Constructs a new {@code Task} with the specified description, task type, and completion status.
     *
     * @param description The description of the task.
     * @param tasktype    The type of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, TaskType tasktype, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tasktype = tasktype;
    }

    /**
     * Returns the status icon for the task.
     * "X" indicates that the task is completed, and " " (space) indicates it is not.
     *
     * @return The status icon ("X" or " ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting its {@code isDone} attribute to {@code true}.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its {@code isDone} attribute to {@code false}.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the prefix for the task's file format, which includes the completion status.
     *
     * @return A string representing the file format prefix for the task.
     */
    public String toFileFormatPrefix() {
        return isDone ? " | 1 | " : " | 0 | ";
    }

    /**
     * Converts the task into a string suitable for saving to a file.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

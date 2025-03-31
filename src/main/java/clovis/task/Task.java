package clovis.task;

/**
 * The {@code Task} class represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructs a new {@code Task} instance with a specified description and set it as uncompleted.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} instance with a specified description and completion status.
     *
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task, indicating whether its completed.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markAsNotDone() {
        isDone = false;
    }


    /**
     * Checks whether this task conflicts with another task. By default, tasks do not have scheduling conflicts.
     * Subclasses such as {@code Deadline} and {@code Event} will override this method to implement conflict
     * detection based on time.
     *
     * @param task the task to check for a conflict with.
     * @return {@code false} since generic tasks do not have conflicts.
     */
    public boolean conflictsWith(Task task) {
        return false;
    }

    /**
     * Returns the string representation of the task formatted for file storage.
     *
     * @return the file format representation of the task.
     */
    public abstract String toFileFormat();

    /**
     * Returns the string representation of the task.
     *
     * @return a string containing the status icon and task description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

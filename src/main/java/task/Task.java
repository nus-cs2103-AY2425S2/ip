package task;

/**
 * Represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" represents a completed task, while " " represents an incomplete task.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A string representing the task in a savable format.
     */
    public String toSaveFormat() {
        return description;
    }

    /**
     * Creates a Task object from a string representation in save format.
     *
     * @param line A string representing a saved task.
     * @return A Task object corresponding to the saved data.
     * @throws IllegalArgumentException If the task type is invalid.
     */
    public static Task fromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];

        switch (type) {
        case "T":
            return Todo.fromSaveFormat(line);
        case "D":
            return Deadline.fromSaveFormat(line);
        case "E":
            return Event.fromSaveFormat(line);
        default:
            throw new IllegalArgumentException("Invalid task type in file");
        }

    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string describing the task with its completion status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
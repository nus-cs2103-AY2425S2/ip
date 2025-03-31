package clovis.task;

/**
 * The {@code ToDo} class represents a task with a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} instance with the specified description.
     *
     * @param description the description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code ToDo} instance with the specified description and completion status.
     * For storage and retrieval purposes.
     *
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the task formatted for file storage.
     *
     * @return the file format of the task with its task type, completion status, and description.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string containing the task type, status icon, and task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

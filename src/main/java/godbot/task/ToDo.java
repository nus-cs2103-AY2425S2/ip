package godbot.task;

/**
 * Represents a ToDo task that contains a description and completion status.
 * Inherits common properties from the {@link Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with a description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The completion status of the task (true if done, false otherwise).
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Constructs a {@code ToDo} task with a description.
     * The task is initially marked as not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task for display purposes.
     *
     * @return A formatted string representing the ToDo task, including its status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo task into a file-friendly format for storage.
     *
     * @return A formatted string representing the ToDo task in file storage format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}


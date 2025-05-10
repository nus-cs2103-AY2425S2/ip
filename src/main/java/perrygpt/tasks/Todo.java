package perrygpt.tasks;

/**
 * The {@code Todo} class represents a simple task that contains only a description
 * and a completion status. It is a subclass of the {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    /**
     * Converts the {@code Todo} task to a file format string suitable for saving.
     *
     * @return A string representing the {@code Todo} task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormatPrefix() + super.description;
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     *
     * @return A string representing the {@code Todo} task, including its type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
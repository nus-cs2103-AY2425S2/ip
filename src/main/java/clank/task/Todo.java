package clank.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with a description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a format suitable for saving to a file.
     *
     * @return The formatted string for saving.
     */
    @Override
    public String toSaveFormat() {
        return "T|" + isDone + "|" + description;
    }

    /**
     * Converts the Todo task to a string representation.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

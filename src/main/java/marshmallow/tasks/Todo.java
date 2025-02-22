package marshmallow.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s", super.toSaveString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

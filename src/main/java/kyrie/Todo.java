package kyrie;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * 
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name, TaskType.TODO);
    }

    /**
     * Constructor for Todo.
     * 
     * @param name The name of the todo task.
     * @param tags The tags of the todo task.
     */
    public Todo(String name, TagList tags) {
        super(name, TaskType.TODO, tags);
    }

    /**
     * Returns the string representation of the todo task, suitable for saving to file.
     * 
     * @return The string representation of the todo task, suitable for saving to file.
     */
    @Override
    public String toFileString() {
        return super.toFileString();
    }

    /**
     * Returns the string representation of the todo task.
     * 
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
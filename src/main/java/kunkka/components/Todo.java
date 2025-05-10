package kunkka.components;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param name Name of the Todo task.
     * @param isDone Status of the Todo task.
     * @param priority priority of the Todo task.
     */
    public Todo(String name, boolean isDone, int priority) {
        super(name, "T", isDone, priority);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + name + " (Priority: " + priority + ")";
    }
}
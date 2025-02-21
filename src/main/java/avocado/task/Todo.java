package avocado.task;

/**
 * Represents a task with no date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo class.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
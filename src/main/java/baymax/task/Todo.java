package baymax.task;

/**
 * Represents a to-do task in the Baymax chatbot.
 * A to-do task only has a name and a completion status without any time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the given name.
     * The task is initially marked as incomplete.
     *
     * @param name The name of the to-do task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new {@code Todo} task with the given name and completion status.
     *
     * @param name The name of the to-do task.
     * @param isCompleted The completion status of the task.
     */
    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    /**
     * Returns a string representation of the to-do task.
     * The format is: [T] followed by the task status and name.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string suitable for saving the to-do task to a file.
     * The format is: T | isCompleted | Task Name.
     *
     * @return A formatted string representing the to-do task for file storage.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}

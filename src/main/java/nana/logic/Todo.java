package nana.logic;

/**
 * Represents a todo task.
 * A todo task is a task without any specific date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo instance with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
        priority = Priority.LOWER_QUARTILE;
    }

    /**
     * Constructs a new Todo instance with the specified description and completion status.
     *
     * @param description the description of the todo task
     * @param isDone the completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        priority = Priority.LOWER_QUARTILE;
    }

    /**
     * Returns a string representation of the todo task.
     * The string representation includes the task type and the task description.
     *
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for storage.
     * The string representation includes the completion status, task type, and task description.
     *
     * @return a string representation of the todo task for storage
     */
    @Override
    public String toStorage() {
        return toStorageIsDone() + " T todo " + description;
    }


}

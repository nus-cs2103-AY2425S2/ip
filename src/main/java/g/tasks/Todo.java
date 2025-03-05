package g.tasks;

/**
 * Represents a simple to-do task without any date/time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with a given description.
     * The task is initially set as not done.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Indicates whether the task is completed.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representing the task status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string to be saved in the storage file.
     *
     * @return A string in the format: `T | 1/0 | description`.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

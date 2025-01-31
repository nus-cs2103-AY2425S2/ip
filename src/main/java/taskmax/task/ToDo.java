package taskmax.task;

/**
 * Represents a ToDo task, which has a description but no specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return A formatted string displaying the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package taskmax.task;

public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description and priority.
     *
     * @param description The description of the ToDo task.
     * @param priority    The priority of the task.
     */
    public ToDo(String description, int priority) {
        super(description, TaskType.TODO, priority);
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

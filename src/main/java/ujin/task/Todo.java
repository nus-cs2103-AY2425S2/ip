package ujin.task;

/**
 * Represents a todo task that extends the {@link Task} class. A todo task is a type of task
 * that does not have any specific date or time associated with it. It is simply a task that
 * needs to be completed.
 * This class provides a specific string representation for todo tasks, which includes
 * the task type identifier "[T]" followed by the task's description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a {@link Todo} task with the specified description. The task is initially marked as not done.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task. The format is "[T]" followed by the task's
     * completion status and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

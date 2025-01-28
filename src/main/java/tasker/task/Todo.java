package tasker.task;

/**
 * A task to do.
 */
public class Todo extends Task {
    /**
     * Class contructor.
     *
     * @param description Description of this task.
     */
    public Todo(String description) {
        super(description, TaskType.T);
    }

    /**
     * Class contructor.
     *
     * @param description Description of this task.
     * @param isDone      Whether this task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.T, isDone);
    }
}

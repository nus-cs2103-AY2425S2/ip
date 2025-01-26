package tasker.task;

/**
 * A task to do.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.T);
    }

    public Todo(String description, boolean isDone) {
        super(description, TaskType.T, isDone);
    }
}

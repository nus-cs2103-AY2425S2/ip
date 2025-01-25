package tasker.task;

/**
 * A task to do.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }
}

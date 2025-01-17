package tasker.task;

/**
 * A task.
 */
public abstract class Task {
    /** The description of the task. */
    private String description;
    /** Status if the task is done. */
    private boolean isDone = false;

    /**
     * Class constructor.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks this task as done.
     */
    void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}

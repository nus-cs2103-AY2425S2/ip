package tasker.task;

/**
 * A task.
 */
public abstract class Task {
    /** The description of the task */
    private String description;
    /** Status if the task is done */
    private boolean isDone = false;
    /** Task type */
    private TaskType type;

    /**
     * Class constructor.
     *
     * @param description Description of this task.
     * @param type The type of this task.
     */
    Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Class constructor specifying isDone.
     *
     * @param description Description of this task.
     * @param type The type of this task.
     * @param isDone Whether this task is done.
     */
    Task(String description, TaskType type, boolean isDone) {
        this(description, type);
        this.isDone = isDone;
    }

    /**
     * Sets isDone value.
     */
    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string for saving and loading this task.
     * 
     * @returns A storage representation string of this task.
     */
    public String toStorage() {
        return String.format("%s|%s|%s", this.type, this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.isDone ? "X" : " ", this.description);
    }
}

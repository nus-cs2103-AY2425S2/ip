package tasker.task;

/**
 * A task.
 */
public abstract class Task {
    /** The description of the task */
    private String description;
    /** Status if the task is done */
    private boolean isDone = false;

    /**
     * Class constructor.
     *
     * @param description Description of this task.
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Setter for isDone.
     */
    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}

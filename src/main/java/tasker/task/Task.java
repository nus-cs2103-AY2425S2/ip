package tasker.task;

/**
 * A task.
 */
public abstract class Task {
    /** The description of the task */
    private String description;
    /** Status if the task is done */
    private boolean isDone = false;
    /** Task type label */
    private String label;

    /**
     * Class constructor.
     *
     * @param description Description of this task.
     * @param label The label of this task.
     */
    Task(String description, String label) {
        this.description = description;
        this.label = label;
    }

    /**
     * Class constructor specifying isDone.
     *
     * @param description Description of this task.
     * @param label The label of this task.
     * @param isDone Whether this task is done.
     */
    Task(String description, String label, boolean isDone) {
        this(description, label);
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
        return String.format("%s|%s|%s", this.label, this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.label, this.isDone ? "X" : " ", this.description);
    }
}

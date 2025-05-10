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
     * @param type        The type of this task.
     */
    Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Class constructor specifying isDone.
     *
     * @param description Description of this task.
     * @param type        The type of this task.
     * @param isDone      Whether this task is done.
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
     * Checks if the task description contains a string.
     *
     * @param term The string which the description must contain.
     * @return A boolean of the result
     */
    boolean contains(String term) {
        return description.toLowerCase().contains(term.toLowerCase());
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task other = (Task) obj;
        return isDone == other.isDone
                && (description == null ? other.description == null : description.equals(other.description))
                && (type == null ? other.type == null : type.equals(other.type));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + Boolean.hashCode(isDone);
        result = 31 * result + (type == null ? 0 : type.hashCode());
        return result;
    }
}

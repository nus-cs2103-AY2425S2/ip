package viktor.tasks;

/**
 * Represents an todo task with a description.
 */
public class TodoTask extends Task {
    private final String type;

    /**
     * Constructs a Todo task with a description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns the type of the task.
     * \
     * @return The task type as a string.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + super.toString();
    }

    /**
     * Returns the string format for saving the todo task.
     *
     * @return The formatted string for saving the task.
     */
    @Override
    public String toSave() {
        return getType() + " | " + getStatusIcon() + " | " + description;
    }
}

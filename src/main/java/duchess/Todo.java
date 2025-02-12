package duchess;
/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private static final String TASK_SYMBOL = "T";
    /**
     * Constructs a Todo task with the given name.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the Todo task in file storage format.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format(
            "%s | %s | %s",
            TASK_SYMBOL,
            this.getIsDone() ? 1 : 0,
            this.getTaskname());
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string showing the task type and name.
     */
    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        return s;
    }
}

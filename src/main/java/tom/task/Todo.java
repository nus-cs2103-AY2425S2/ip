package tom.task;

/**
 * Represents an todo task.
 */
public class Todo extends Task {
    /**
     * Constructs an Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task in file format.
     *
     * @return A string in the format "T | statusIcon | description".
     */
    @Override
    public String toFileFormatString() {
        return String.format("T | %s | %s", getStatusIcon(), getDescription());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[T][statusIcon] description (from: startDate to: endDate)".
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

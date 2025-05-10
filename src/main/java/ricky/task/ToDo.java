package ricky.task;

/**
 * Represents a task without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(final String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of the ToDo task for storage.
     *
     * @return A string representation of the ToDo task for storage.
     */
    @Override
    public String storeInfo() {
        return String.format("T | %d | %s", super.getIsDone() ? 1 : 0, description);
    }
}

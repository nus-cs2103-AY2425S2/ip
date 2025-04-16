package pluto;

/**
 * Represents a todo task
 * This task has a description, but no specified
 * start or end date
 */
public class ToDo extends Task {
    /**
     * Creates a new task with no specified end date
     * @param description a String that describes the task
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.taskStatusMessage();
    }

    /**
     * Converts the task to file format to be stored in tasks file
     * @return a String to be stored in the tasks file
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public boolean isScheduledFor(String dateInput) {
        return false; // ToDo tasks have no specific date attached
    }
}

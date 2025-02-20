package prophet.task;

/**
 * The ToDoTask class represents a task that needs to be done.
 */
public class ToDoTask extends Task {
    private static final String TYPE = "T | ";

    /**
     * Initialises a newly created ToDoTask object with a description.
     * @param description the description of the task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string that visually indicates the status of the to-do item.
     * @return the string that represents the task type and status
     */
    @Override
    public String getStatusIcon() {
        return ToDoTask.TYPE + super.getStatusIcon();
    }

    /**
     * Returns a string representation of the to do.
     * @return the string that represents the to do description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + super.getTaskDescription() + "\n";
    }
}

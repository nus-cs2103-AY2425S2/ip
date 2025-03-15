package seedu.SirDuke.backend.task;

/**
 * Represents a task without a start date or a deadline.
 */
public class ToDoTask extends Task {

    /**
     * Constructor for a Task object
     *
     * @param description name of the task
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toFileEntry() {
        return "T|" + getStatusIcon() + "|" + description;
    }

    @Override
    public String getTaskType() {
        return "TODO";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

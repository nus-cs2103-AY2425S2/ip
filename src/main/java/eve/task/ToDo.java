package eve.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return "T | " + super.getStatusIcon() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

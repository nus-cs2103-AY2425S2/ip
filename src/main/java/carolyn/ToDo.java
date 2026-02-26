package carolyn;

/**
 * Represents a basic to-do task with a description.
 * A ToDo task does not have a deadline or specific time.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
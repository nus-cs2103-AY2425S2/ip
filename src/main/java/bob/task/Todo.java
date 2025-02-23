package bob.task;

/**
 * This class represents a todo task (inherits from Task).
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the given description.
     *
     * @param description String.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo object with the given description and completed status.
     *
     * @param description String.
     * @param completed Boolean.
     */
    public Todo(String description, boolean completed) {
        super(description, completed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toCsv() {
        return String.format("T,%s,%b,,,", this.description, this.isCompleted);
    }
}

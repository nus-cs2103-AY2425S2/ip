package phil.model;

/**
 * Represents a Todo task which has a description.
 */
public class Todo extends Task {

    /**
     * Sets up the Event object.
     *
     * @param description String which describes the event task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of Todo object for printing when list is called.
     *
     * @return String representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representation of Todo object for use by Storage class.
     *
     * @return String representation of the Todo object to be stored and loaded by Storage class.
     */
    @Override
    public String toLoadString() {
        return "Todo - " + super.toLoadString();
    }
}

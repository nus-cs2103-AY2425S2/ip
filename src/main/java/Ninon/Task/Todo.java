package Ninon.Task;

/**
 * Represents a to-do task without any specific date or time constraints.
 * Inherits from the {@code Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its status
     * and description.
     *
     * @return a formatted string representing the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the to-do task for output, following the format:
     * "T / [completion status] / description".
     *
     * @return a formatted string suitable for saving to-do task details
     */
    @Override
    public String formatOut() {
        return "T / " + super.formatOut();
    }
}
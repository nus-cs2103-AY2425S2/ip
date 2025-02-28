package gptzerofive.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a string format suitable for file storage.
     *
     * @return A string representation of the todo task for file storage.
     */
    @Override
    public String convertToFileString() {
        return "T | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | " + super.getNote();
    }

}

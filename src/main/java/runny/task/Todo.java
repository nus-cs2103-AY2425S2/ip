package runny.task;


/**
 * Represents a todo task in Runny Chatbot.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the given name.
     *
     * @param description The name of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to a string for saving to a file.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String save() {
        return (super.isDone ? "1 " : "0 ") + "todo " + super.description;
    }

    /**
     * Converts the todo task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

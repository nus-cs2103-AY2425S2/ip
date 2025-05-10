package elchino.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeTask() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

}
package woof.task;

/**
 * Represents a todo task with a description only.
 */
public class Todo extends Task {
    public Todo(String string) {
        super(string);
    }

    /**
     * Returns a description of the task to be displayed on CLI.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the description of the task to be recorded locally.
     *
     * @return The description of the task.
     */
    @Override
    public String print() {
        return String.format("T | %s", super.print());
    }
}

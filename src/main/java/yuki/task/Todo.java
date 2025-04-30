package yuki.task;

/**
 * Represents a task in the task list.
 */
public class Todo extends Task {
    public Todo(String isDone, String taskName) {
        super(taskName, isDone.equals("1"));
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

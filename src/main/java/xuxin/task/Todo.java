package xuxin.task;

/**
 * Represents a task to be done later,
 */
public class Todo extends Task {
    private static String statum = "[T]";

    public Todo(String name) {
        super(name, false);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return statum + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.name;
    }
}

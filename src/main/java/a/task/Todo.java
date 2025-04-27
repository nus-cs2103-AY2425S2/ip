package a.task;

/**
 * task of type todo
 */
public class Todo extends Task {
    protected TaskType type = TaskType.TODO;
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

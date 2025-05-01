package hamiltonianguy.tasks;

public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getType() + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDesc();
    }
}

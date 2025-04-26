package task;

/*
Task with a description
 */
public class ToDo extends Task{

    public ToDo(String name, int priority) {
        super(name, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "[T]" + " " + getStatus() + " " + getPriority() + " " + getDescription();
    }
}

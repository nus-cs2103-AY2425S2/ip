package wizt.task;
/**
 *  Represents Todo Tasks
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * @return ToDo task in a specified format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

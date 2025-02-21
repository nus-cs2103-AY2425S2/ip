package bobandsteve.task;

/**
 * Represents a Todo task in the BobAndSteve application.
 * A Todo task has a description, a status (completed or not), a specific type of task.
 */
public class Todo extends Task {

    private String by;

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone The status of the Todo task (whether it's completed or not).
     */
    public Todo(String description, String isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }

}

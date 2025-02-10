package julie.task;

/**
 * Represents a ToDo task in the task list.
 * A ToDo task contains only a description without any date or time constraints.
 */
public class ToDo extends Task {
    private static final String MARKER = "[T]";

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task into a formatted string suitable for file storage.
     * The format follows: "T | {isDone} | {description}".
     *
     * @return A string representation of the ToDo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task, including its status and description.
     *
     * @return A formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return MARKER + " " + super.toString();
    }
}



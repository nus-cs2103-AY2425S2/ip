package julie.task;

/**
 * Represents a ToDo task in the task list.
 * A ToDo task contains only a description without any date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks if this ToDo task is equal to another object.
     * Two ToDo tasks are considered equal if they have the same description.
     *
     * @param obj The object to compare with this ToDo task.
     * @return {@code true} if the given object is a ToDo task with the same description, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) obj;
        return this.description.equals(other.description);
    }

    /**
     * Returns the marker representing a ToDo task.
     * The marker "[T]" signifies that this task is a ToDo.
     *
     * @return The string marker "[T]".
     */
    @Override
    protected String getMarker() {
        return "[T]";
    }

    /**
     * Converts the ToDo task into a formatted string suitable for file storage.
     * The format follows: "T | {isDone} | {description}".
     *
     * @return A string representation of the ToDo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + priority;
    }

    /**
     * Returns a string representation of the ToDo task, including its status and description.
     *
     * @return A formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return this.getPriorityIcon() + " " + this.getMarker() + " " + super.toString();
    }
}

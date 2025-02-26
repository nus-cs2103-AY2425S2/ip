package Krypto.Task;


/**
 * Represents a To-Do task.
 */
public class ToDo extends Task {
    /**
     * Constructs a To-Do task with the specified description.
     *
     * @param description The description of the To-Do task.
     */
    public ToDo(String description) {
        super(extractContent(description.split("/")[0].split(" ")));
    }

    /**
     * Returns the string representation of the To-Do task.
     *
     * @return A formatted string representing the To-Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the To-Do task to a string format suitable for file storage.
     *
     * @return A string representation of the To-Do task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (super.getStatusIcon()) + " | " + description;
    }
}
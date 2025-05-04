package softess;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string indicating the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Marks the task as done.
     *
     * @return A message indicating that the task has been marked as done.
     */
    @Override
    public String markAsDone() {
        super.isDone = true;
        return "Nice! I've marked this task as done: \n " + this.toString();
    }

    /**
     * Marks the task as undone.
     *
     * @return A message indicating that the task has been marked as undone.
     */
    @Override
    public String markAsUnDone() {
        super.isDone = false;
        return "Nice! I've marked this task as undone: \n " + this.toString();
    }

    /**
     * Generates a formatted string for file storage.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String generateTextToFile() {
        int status = this.isDone ? 1 : 0;
        String result = "TODO|%d|%s".formatted(status, this.description);
        return result;
    }
}

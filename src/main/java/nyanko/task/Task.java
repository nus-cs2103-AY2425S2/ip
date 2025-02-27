package nyanko.task;

/**
 * Represents a generic task in the Nyanko task manager.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task (X for done, empty for not done).
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Returns the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
    * Marks the task as not done.
    */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the task into a format suitable for saving to a file.
     *
     * @return A formatted string representing the task.
     */
    public String toSaveFormat() {
        return String.format("%s|%d|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description);
    }

    /**
     * Converts a saved string format back into a Task object.
     *
     * @param saveFormat The saved string format.
     * @return A Task object reconstructed from the string.
     * @throws InvalidTaskFormatException If the format is invalid.
     */
    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "ToDo":
            task = new ToDo(description);
            break;
        case "Deadline":
            if (parts.length < 4) {
                throw new InvalidTaskFormatException("Invalid deadline format: " + saveFormat);
            }
            task = new Deadline(description, parts[3]);
            break;
        case "Event":
            if (parts.length < 5) {
                throw new InvalidTaskFormatException("Invalid event format: " + saveFormat);
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new InvalidTaskFormatException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

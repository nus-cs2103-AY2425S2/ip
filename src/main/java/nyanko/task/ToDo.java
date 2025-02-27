package nyanko.task;

/**
 * Represents a ToDo task in the Nyanko application.
 * A ToDo task contains only a description and does not have a deadline or event duration.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type identifier `[T]` and completion status.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo task into a save-friendly string format.
     *
     * @return A formatted string representing the task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return String.format("%s|%d|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description);
    }

    /**
     * Creates a ToDo task from a given save format string.
     *
     * @param saveFormat The string containing the saved task details.
     * @return A ToDo task reconstructed from the saved format.
     * @throws InvalidTaskFormatException If the format is incorrect.
     */
    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length != 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        if (!parts[1].equals("0") && !parts[1].equals("1")) {
            throw new InvalidTaskFormatException("Invalid completion status in ToDo task: "
                    + parts[1] + ". Expected '0' or '1'.");
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (description.isEmpty()) {
            throw new InvalidTaskFormatException("Invalid ToDo task: description is empty. Data: " + saveFormat);
        }

        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }
}

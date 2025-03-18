package clank.task;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Parses a saved task string and converts it into a Task object.
     * This method is used to restore tasks from a saved file.
     *
     * @param line The saved task string in the format "T|isDone|description" for Todo,
     *             "D|isDone|description|by" for Deadline, and
     *             "E|isDone|description|start|end" for Event.
     * @return The corresponding {@code Task} object if parsing is successful,
     *         otherwise {@code null} if the task is corrupted or unknown.
     */
    public static Task fromSavedFormat(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 3) {
            System.out.println("Corrupted task.");
            return null;
        }

        try {
            String taskType = parts[0];
            boolean isDone = Boolean.parseBoolean(parts[1]);
            String description = parts[2];

            Task task = createTask(taskType, description, parts);

            if (task == null) {
                System.out.println("Unknown task type: " + taskType);
                return null;
            }

            if (isDone) {
                task.mark();
            }

            return task;
        } catch (Exception e) {
            System.out.println("Corrupted task.");
            return null;
        }
    }

    /**
     * Creates a Task object based on the task type and its details.
     * This method is used within {@code fromSavedFormat()} to construct the appropriate Task subclass.
     *
     * @param taskType The type of task ("T" for Todo, "D" for Deadline, "E" for Event).
     * @param description The description of the task.
     * @param parts The full array of task details parsed from the saved file.
     * @return The corresponding {@code Task} object if creation is successful,
     *         otherwise {@code null} if the task is corrupted or unknown.
     */
    private static Task createTask(String taskType, String description, String[] parts) {
        switch (taskType) {
        case "T":
            return new Todo(description);
        case "D":
            if (parts.length < 4) {
                System.out.println("Corrupted deadline task. Date is missing.");
                return null;
            }
            return new Deadline(description, parts[3]);
        case "E":
            if (parts.length < 5) {
                System.out.println("Corrupted event task. Start or End date is missing.");
                return null;
            }
            return new Event(description, parts[3], parts[4]);
        default:
            return null;
        }
    }

    /**
     * Converts the task into a format suitable for saving to a file.
     * Subclasses must implement this method to define their own save format.
     *
     * @return A formatted string representation of the task for storage.
     */
    public abstract String toSaveFormat();
}

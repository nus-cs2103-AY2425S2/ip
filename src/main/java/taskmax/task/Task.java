package taskmax.task;

/**
 * Represents a generic task with a description, completion status, and type.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private final TaskType type;
    private final int priority;

    /**
     * Constructs a Task with a given description, type, and priority.
     *
     * @param description The description of the task.
     * @param type        The type of the task (ToDo, Deadline, Event).
     * @param priority    The priority of the task (e.g., 1 for high, 2 for medium, 3 for low).
     */
    public Task(String description, TaskType type, int priority) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.priority = priority;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon representing task completion.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string displaying the task's status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Retrieves the priority of the task.
     *
     * @return The priority of the task.
     */
    public int getPriority() {
        return priority;
    }
}

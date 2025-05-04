package softess;

/**
 * Represents an abstract task.
 * A {@code Task} consists of a description and a completion status.
 * This class provides methods to mark a task as done or undone, retrieve its status,
 * and generate a string representation of the task.
 *
 *
 * @author Hrishikesh Sathyian
 */
public abstract class Task {

    /** The description of the task */
    protected String description;

    /** The completion status of the task */
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * The task is initially marked as not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If the task is completed, it returns "X"; otherwise, it returns a space " ".
     *
     * @return The status icon representing the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed and prints a confirmation message.
     */
    public String markAsDone() {
        this.isDone = true;
        String res = "Nice! I've marked this task as done: \n [X] " + this.description;
        return res;
    }

    /**
     * Marks this task as not completed and prints a confirmation message.
     */
    public String markAsUnDone() {
        this.isDone = false;
        String res = "Nice! I've marked this task as undone: \n [ ] " + this.description;
        return res;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A string containing the status icon and the task description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Generates a formatted string representation of the task for file storage.
     * This method must be implemented by subclasses.
     *
     * @return A string representation of the task suitable for file storage.
     */
    public abstract String generateTextToFile();
}

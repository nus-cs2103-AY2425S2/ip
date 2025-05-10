package partillay.task;

/**
 * Represents a generic task with a description and status (done or not done).
 * This is an abstract class, intended to be extended by specific task types (e.g., Deadline, ToDo, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given description and status.
     * The status is represented by a binary number, where "1" means done and "0" means not done.
     *
     * @param description the description of the task
     * @param statusBinaryNumber the status of the task in binary format ("1" for done, "0" for not done)
     */
    public Task(String description, String statusBinaryNumber) {
        this.description = description;
        this.isDone = statusBinaryNumber.equals("1");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the icon representing the status of the task.
     * An "X" represents a completed task, and a space represents an incomplete task.
     *
     * @return a string icon representing the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusBinaryNumber() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the Task in a format suitable for saving to a file.
     * The format will depend on the specific task type and should be implemented in subclasses.
     *
     * @return a string representation of the Task in a format suitable for file storage
     */
    public abstract String getTxtFormat();
}

package phil.model;

/**
 * Represents the abstract Task class, which concrete classes Todo, Event, Deadline extend from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Sets up the Task object.
     *
     * @param description String which describes the event task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon based on whether the task is done (X) or not ( ).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of Task object for printing when list is called.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns String representation of Task object for use by Storage class.
     *
     * @return String representation of the Task object to be stored and loaded by Storage class.
     */
    public String toLoadString() {
        return this.getStatusIcon() + " - " + this.description;
    }

    /**
     * Returns whether string is in description.
     *
     * @param str String search term to check in description.
     * @return boolean representing whether string is in description.
     */
    public boolean descContains(String str) {
        return this.description.contains(str);
    }
}

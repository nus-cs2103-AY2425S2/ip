package ziyang;

import java.io.Serializable;

/**
 * Represents a basic task in the task management system.
 * This class serves as the parent class for specific task types.
 * Implements Serializable to allow for storage persistence.
 */
public class Task implements Serializable {
    /** The description of the task */
    protected String description;
    
    /** The completion status of the task */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * @return "[X]:" if the task is done, "[ ]:" if not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]:" : "[ ]:");
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }
}

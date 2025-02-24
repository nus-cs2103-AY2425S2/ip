package runny.task;


/**
 * Represents a task in the Runny Chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task instance with the given name.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the tasks completion in the form of a symbol.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done runny.task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    public String save() {
        return "";
    };

    /**
     * Retrieves the completion status of the task.
     *
     * @return `true` if the task is completed, `false` otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task to a string.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

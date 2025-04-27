package stonks.task;

/**
 * An item to be completed
 * Has a description and completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s",isDone ? "X" : " ", description);
    }

    /**
     * Mark as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Mark as undone
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Converts task to a format for saving in file
     * @return string to be saved in file
     */
    public String toFileFormat() {
        return this.toString();
    }

    public boolean contains(String searchString) {
        return this.description.toLowerCase().contains(searchString.toLowerCase());
    }

    /**
     * Check if two tasks are the same
     * @param task task to be compared
     * @return
     */
    public boolean isSameTask(Task task) {
        return this.description.equals(task.description);
    }
}
package SirDuke.backend.task;

import SirDuke.backend.Storage;

/**
 * Represents a task. A <code>Task<code> object has
 * a <code>description<code> String member that is the name of the task
 * a <code>isDone<code> boolean member that represents whether the task is completed or not
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object
     * <p>
     * <code>boolean  isDone</code> is set to false by default
     * because we assume the task is not completed at the time of adding to the list
     * @param description name of the task
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * @return string representing the Task in a format that is readable by storage
     * @see Storage , to ensure that readEntry(String entry) can read this format
     */
    public abstract String toFileEntry();

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * @return "X" if done, to show that task has been marked as done and " " otherwise
     * */
    public String getStatusIcon() {
        return isDone
                ? "X"
                : " ";
    }

    /**
     * Marks a task as done
     * <p>
     * Even if the task is already done, this method will not throw an exception
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as done
     * <p>
     * Even if the task is not yet done, this method will not throw an exception
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}

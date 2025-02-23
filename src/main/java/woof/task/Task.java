package woof.task;

import woof.exception.MarkedErrorException;
import woof.exception.UnmarkedErrorException;

/**
 * Represents a general task which is never used in this project.
 */
public class Task {
    private boolean isDone;
    private String task;

    /**
     * Create an instance of a general task which is never used in this project.
     *
     * @param task Description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Returns the common description of the task to be displayed on CLI.
     *
     * @return The description of the task.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (isDone) {
            sb.append("X");
        } else {
            sb.append(" ");
        }
        sb.append("] ").append(task);
        return sb.toString();
    }

    /**
     * Mark the task as done.
     *
     * @throws MarkedErrorException Task is already marked as done.
     */
    public void markAsDone() throws MarkedErrorException {
        if (isDone) {
            throw new MarkedErrorException();
        }
        isDone = true;
    }

    /**
     * Unmark the task as done.
     *
     * @throws UnmarkedErrorException Task is already not marked as done.
     */
    public void unmarkAsDone() throws UnmarkedErrorException {
        if (!isDone) {
            throw new UnmarkedErrorException();
        }
        isDone = false;
    }

    /**
     * Returns the common description of the task to be recorded locally.
     *
     * @return The description of the task.
     */
    public String print() {
        return String.format("%d | %s", isDone ? 1 : 0, task);
    };

    public String getDescription() {
        return task;
    }
}

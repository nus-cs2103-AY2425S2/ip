package tom.task;

import tom.exception.TaskException;
import tom.parser.WordMatch;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
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
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     *
     * @throws TaskException If the task is already done.
     */
    public void markDone() throws TaskException {
        if (isDone) {
            throw new TaskException("Task is already done.");
        }
        isDone = true;
    }

    /**
     * Checks if the task description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return true if the keyword is found in the description, false otherwise.
     */
    public boolean matchKeyword(String keyword) {
        int threshold = 2; // Maximum allowed edit distance

        for (String word : description.split("\\s+")) {
            if (WordMatch.levenshteinDistance(word.toLowerCase(), keyword.toLowerCase()) <= threshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * Marks the task as not done.
     *
     * @throws TaskException If the task is already not done.
     */
    public void markUndone() throws TaskException {
        if (!isDone) {
            throw new TaskException("Task is already not done.");
        }
        isDone = false;
    }

    /**
     * Returns a string representation of the task in file format.
     *
     * @return A string in the format "_ | statusIcon | description".
     */
    public String toFileFormatString() {
        return String.format("_ | %s | %s", getStatusIcon(), getDescription());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}

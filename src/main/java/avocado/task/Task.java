package avocado.task;

import java.util.Set;
import java.util.HashSet;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected  String description;
    protected  boolean isDone;
    protected Set<String> tags;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Add a tag to the task.
     * 
     * @param tag
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Remove a tag from the task.
     * 
     * @param tag
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Check if the task has a tag.
     * 
     * @param tag
     * @return
     */

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public String getTagsAsString() {
        return tags.isEmpty() ? "" : " " + tags;
    }

    /**
     * Gets the status of the task.
     * @return The status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + getTagsAsString();
    }
}






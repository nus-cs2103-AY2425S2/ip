package carolyn;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task with a description, completion status, and optional tags.
 * Implements {@link Serializable} to allow task objects to be serialized.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Constructs a Task with a given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Returns the status icon of the task.
     * "X" represents a completed task, while a space " " represents an incomplete task.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return (description);
    }

    /**
     * Returns a formatted string representing the list of tags.
     * Tags are prefixed with "#" and separated by spaces.
     *
     * @return A string containing all the tags associated with the task.
     */
    public String getTagsList() {
        String s = "";
        for (String item : tags) {
            s += "#" + item + " ";
        }
        return s;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description + " " + this.getTagsList());
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tagString The tag to be added.
     */
    public void tag(String tagString) {
        this.tags.add(tagString);
    }
}

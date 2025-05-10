package kaji.task;

import java.util.HashSet;
import java.util.Set;

import kaji.Tag;

/**
 * Represents a task with a type, completion status, and description.
 */
public class Task {
    private String description;
    private boolean isDone;
    private final String type;
    private Set<Tag> tags;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;
        this.tags = new HashSet<>();
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed by setting its status to done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks the task as uncompleted by setting its status to not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Returns the status of task.
     *
     * @return A boolean value representing its status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task type.
     *
     * @return A string representing task type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the task description.
     *
     * @return A string representing task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds a tag to a task.
     *
     * @param tagName The name of the tag to be added to the task.
     */
    public void addTag(String tagName) {
        tags.add(new Tag(tagName));
    }

    /**
     * Removes a tag from the task.
     *
     * @param tagName The name of the tag to be removed from the task.
     */
    public void removeTag(String tagName) {
        Tag tagToRemove = new Tag(tagName);
        tags.remove(tagToRemove);
    }

    /**
     * Returns a chain of tag list related to a task.
     *
     * @return A tag list in readable format.
     */
    public String getTagsString() {
        return tags.stream()
                .map(Tag::toString) // Convert each Tag object to its string representation
                .reduce((t1, t2) -> t1 + ", " + t2) // Combine tags with a comma separator
                .orElse("");
    }

    /**
     * Adds multiple tags to the task.
     *
     * @param tags A comma-separated string of tag names to be added.
     */
    public void addAllTags(String tags) {
        String[] tagList = tags.split(", ");
        for (String tag : tagList) {
            this.addTag(tag);
        }
    }

    /**
     * Checks if the task contains a specific tag.
     *
     * @param tagName The name of the tag to check.
     * @return true if the task contains the tag, false otherwise.
     */
    public boolean containsTag(String tagName) {
        return tags.contains(new Tag(tagName));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The task's description as a string.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (%s)",
                type, getStatusIcon(), description, getTagsString()
        );
    }
}

package miki.task;

import java.time.LocalDateTime;
import java.util.Arrays;

import miki.exception.MikiException;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isCompleted;
    private TaskType taskType;
    private String[] tags;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     *
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskType taskType, String... tags) {
        this.description = description;
        this.isCompleted = false;
        this.taskType = taskType;
        this.tags = tags;
    }

    /**
     * Parses a task from a line in the storage file.
     *
     * @param line The line to parse the task from.
     *
     * @return The task parsed from the line.
     */
    public static Task parseTaskFromFile(String line) throws MikiException {
        String[] tokens = line.split(" \\| ");

        try {
            Task task = null;
            if (tokens[0].equals("D")) {
                String description = tokens[2];
                LocalDateTime deadline = LocalDateTime.parse(tokens[4]);
                String[] tags = tokens.length > 3
                    ? tokens[3].split(" ")
                    : new String[0];
                task = new Deadline(description, deadline, tags);
            } else if (tokens[0].equals("E")) {
                String description = tokens[2];
                LocalDateTime start = LocalDateTime.parse(tokens[4]);
                LocalDateTime end = LocalDateTime.parse(tokens[5]);
                String[] tags = tokens.length > 3
                    ? tokens[3].split(" ")
                    : new String[0];
                task = new Event(description, start, end, tags);
            } else {
                String description = tokens[2];
                String[] tags = tokens.length > 3
                    ? tokens[3].split(" ")
                    : new String[0];
                task = new ToDo(description, tags);
            }
            if (tokens[1].equals("1") && task != null) {
                task.toggleCompletion();
            }
            return task;
        } catch (Exception e) {
            throw new MikiException(e.getMessage());
        }
    }

    /**
     * Toggles the completion status of the task.
     */
    public void toggleCompletion() {
        this.isCompleted = !this.isCompleted;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean checkCompleted() {
        return isCompleted;
    }

    /**
     * Converts the task to a string for storage.
     *
     * @return The task in storage format.
     */
    public String toStorageFormat() {
        return (isCompleted ? "1" : "0") + " | " + description + " | " + getTagsToStorageFormat();
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the description of the task.
     *
     * @return String of the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the tags of the task.
     *
     * @return String of the tags of the task.
     */
    public String getTags() {
        if (tags.length == 0) {
            return "";
        }
        return String.join(" ", Arrays.stream(tags).map(tag -> !tag.isEmpty() ? "#" + tag : "").toArray(String[]::new));
    }

    /**
     * Returns the tags of the task.
     *
     * @return String of the tags of the task in storage format.
     */
    public String getTagsToStorageFormat() {
        if (tags.length == 0) {
            return "";
        }
        return String.join(" ", Arrays.stream(tags).map(tag -> !tag.isEmpty() ? tag : "").toArray(String[]::new));
    }


    /**
     * Returns the task in a string format.
     *
     * @return The task in string format.
     */
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "]  " + description + "\n" + getTags();
    };
}

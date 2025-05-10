package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task.
     * @param description The task description.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets icon for status of task completion.
     * @return "X" for completed task and " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Parses a date string into a LocalDateTime object.
     * @param date The date string in yyyy-MM-dd HHmm format.
     * @return A LocalDateTime object or null if parsing fails.
     */
    public static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Sets the task description.
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the task.
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted string for saving to a file.
     * @return The formatted string.
     */
    public abstract String toFileString();
}


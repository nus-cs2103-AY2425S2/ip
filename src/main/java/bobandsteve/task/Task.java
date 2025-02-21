package bobandsteve.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task in the BobAndSteve application.
 * A task has a description, a completion status, and methods to mark/unmark it as done.
 */
public abstract class Task implements Comparable<Task> {

    private final String description;
    private String done;

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param done The status of the task (whether it's completed or not).
     */
    public Task(String description, String done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Marks the task as completed by setting its status to "[X]".
     */
    public void mark() {
        this.done = "[X]";
    }

    /**
     * Marks the task as not completed by setting its status to "[ ]".
     */
    public void unmark() {
        this.done = "[ ]";
    }

    @Override
    public String toString() {
        return done + " " + description;
    }

    /**
     * Formats a LocalDateTime into a readable string.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string in the pattern "MMM d yyyy, hh:mm a" (e.g., "Sep 10 2024, 02:30 PM").
     */
    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return dateTime.format(formatter).replaceAll("AM", "am").replaceAll("PM", "pm");
    }

    /**
     * Returns a string representation of the task's status and description,
     * formatted for saving to a file.
     *
     * @return A string in the format "1 | task description" if the task is done,
     *         or "0 | task description" if the task is not done.
     */
    public String toSaveFormat() {
        if (done.equals("[X]")) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    public LocalDateTime getDeadline() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Task other) {
        LocalDateTime thisDeadline = this.getDeadline();
        LocalDateTime otherDeadline = other.getDeadline();
        if (thisDeadline == null && otherDeadline == null) {
            return this.description.compareTo(other.description);
        } else if (thisDeadline == null) {
            return 1;
        } else if (otherDeadline == null) {
            return -1;
        }
        int result = thisDeadline.compareTo(otherDeadline);
        if (result == 0) {
            return this.description.compareTo(other.description);
        }
        return result;
    }
}

package ujin.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and a completion status. A task can be marked as
 * done or undone, and it has a description that provides more details about the task.
 * This class provides methods to manage the task's completion status, retrieve the task's
 * description, and display a user interface string representation of the task's status and description.
 */
public class Task {

    /**
     * The formatter of the time.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task, where {@code true} means the task is done and {@code false}
     * means the task is not completed.
     */
    protected boolean isDone;

    /**
     * Constructs a {@link Task} with the given description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon for this task. A task that is done is marked with "X",
     * while a task that is not done is marked with a space.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     * The status is displayed as "[X]" for completed tasks and "[ ]" for incomplete tasks.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String box = "[" + (this.isDone ? "X" : " ") + "]";
        return (box + ' ' + this.description);
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the task's description.
     */
    public String description() {
        return this.description;
    }

    /**
     * Returns whether the task is completed or not.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Parses a string representing a date and time and converts it into a {@link LocalDateTime} object.
     * The string should be in the format "yyyy-MM-dd HH:mm" or "MM-dd HH:mm", and if no year is provided,
     * the current year is used.
     *
     * @param date The date string to be parsed.
     * @return A {@link LocalDateTime} object representing the parsed date and time.
     */
    public LocalDateTime parse(String date) {
        date = date.trim();

        String[] parts = date.split("[-/ ]");
        String year;
        String month;
        String day;
        String time;

        if (date.contains(String.valueOf(' '))) {
            if (parts.length == 4) {
                if (parts[2].length() == 4) {
                    year = parts[2];
                    month = parts[1];
                    day = parts[0];
                } else {
                    year = parts[0];
                    month = parts[1];
                    day = parts[2];
                }
                time = parts[3];
            } else {
                year = String.valueOf(LocalDate.now().getYear());
                month = parts[0];
                day = parts[1];
                time = parts[2];
            }
        } else {
            time = "23:59";
            if (parts.length == 3) {
                if (parts[2].length() == 4) {
                    year = parts[2];
                    month = parts[1];
                    day = parts[0];
                } else {
                    year = parts[0];
                    month = parts[1];
                    day = parts[2];
                }
            } else {
                year = String.valueOf(LocalDate.now().getYear());
                month = parts[0];
                day = parts[1];
            }
        }
        date = year + "-" + month + "-" + day + " " + time;
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns the string of the date
     *
     * @param date The date to be turned.
     * @return String representing the date.
     */
    public String formatTheString(LocalDateTime date) {
        return " (by: " + date.format(formatter) + ")";
    }

    /**
     * Returns the string of a date that has start and end.
     *
     * @param start The start of a date.
     * @param end The end of a date.
     * @return String representing the start and end of a date.
     */
    public String formatTheString(LocalDateTime start, LocalDateTime end) {
        return "(from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}

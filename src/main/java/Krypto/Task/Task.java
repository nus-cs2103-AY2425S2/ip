package Krypto.Task;
import Krypto.Exceptions.KryptoExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone = false;
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");
    private String sign = "";

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Checks if the task is scheduled for the given date.
     *
     * @param date The date to check.
     * @return Always returns false as a generic task has no date.
     */
    public boolean onThisDay(String date) {
        return false;
    }

    /**
     * Extracts the content from a given array of prompt strings.
     *
     * @param prompt The array of strings representing the user input.
     * @return The extracted content as a single string.
     */
    public static String extractContent(String[] prompt) {
        StringBuilder content = new StringBuilder();
        if (prompt.length <= 1) {
            return "";
        }
        for (int i = 1; i < prompt.length; i++) {
            if (i != prompt.length - 1) {
                content.append(prompt[i]).append(" ");
                continue;
            }
            content.append(prompt[i]);
        }
        return content.toString();
    }

    /**
     * Marks the task as completed and updates its status icon.
     */
    public String markTask() {
        this.isDone = true;
        this.sign = "X";
        return "Nice! I've marked this task as done: " + this;
    }

    /**
     * Marks the task as completed and updates its status icon.
     *
     * @param print Whether to print the task status.
     */
    public void markTask(boolean print) {
        this.isDone = true;
        this.sign = "X";
        if (print) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this);
        }
    }

    /**
     * Unmarks the task as not completed and updates its status icon.
     */
    public String unmarkTask() {
        this.isDone = false;
        this.sign = "";
        return "OK! I've marked this task as not done yet: " + this;
    }

    public void setDate(LocalDateTime from, LocalDateTime to) throws KryptoExceptions {
        throw new KryptoExceptions("Sorry I can't reschedule a task without time information");
    }

    public String getStatusIcon() {
        return this.sign;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFileString() {
        return "";
    }

    public boolean hasKeyword(String keyword) {
        String[] parts = description.split(" ");
        for(int i = 0; i < parts.length; i ++) {
            if(keyword.equals(parts[i])) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "[" + this.sign + "] " + this.description;
    }
}

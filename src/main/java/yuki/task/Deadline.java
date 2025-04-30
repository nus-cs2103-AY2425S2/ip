package yuki.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import yuki.YukiException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Creates a deadline task.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String isDone, String taskName, String deadline) throws YukiException {
        super(taskName, isDone.equals("1"));
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new YukiException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    /**
     * Creates a deadline task.
     *
     * @return The string representation of the task stored in file.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

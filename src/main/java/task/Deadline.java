package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline task.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }

    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Creates a Deadline task from a saved file format.
     *
     * @param line The saved format string.
     * @return A Deadline task.
     */
    public static Deadline fromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Deadline task = new Deadline(parts[2], parts[3]);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

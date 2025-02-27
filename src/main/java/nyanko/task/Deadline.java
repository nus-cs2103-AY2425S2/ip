package nyanko.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The task description.
     * @param by The due date and time in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    public String getBy() {
        return this.by.format(INPUT_FORMAT);
    }

    /**
     * Updates the deadline date/time.
     *
     * @param newDateTime The new due date/time in "yyyy-MM-dd HHmm" format.
     */
    public void snooze(String newDateTime) {
        this.by = LocalDateTime.parse(newDateTime, INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string showing the task type, status, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Converts the Deadline task into a save-friendly string format.
     *
     * @return A formatted string representing the task for saving.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("%s|%d|%s|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description, this.by.format(formatter));
    }

    /**
     * Creates a Deadline task from a given save format string.
     *
     * @param saveFormat The string containing the saved task details.
     * @return A Deadline task reconstructed from the saved format.
     * @throws InvalidTaskFormatException If the format is incorrect.
     */
    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 4) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String by = parts[3];

        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }
}

package taskmax.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    private final LocalDateTime dateTime;

    /**
     * Constructs a Deadline task with a description and due date.
     *
     * @param description    The description of the deadline task.
     * @param dateTimeString The due date and time in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String dateTimeString) {
        super(description, TaskType.DEADLINE);
        this.dateTime = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
    }

    /**
     * Retrieves the due date and time of the deadline.
     *
     * @return The due date and time as a LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A formatted string displaying the task type, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(OUTPUT_FORMATTER) + ")";
    }
}

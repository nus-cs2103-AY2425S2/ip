package duchess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} object stores the task name and the due date/time.
 */
public class Deadline extends Task {
    private static final String TASK_SYMBOL = "D";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private LocalDateTime by;



    /**
     * Constructs a new Deadline task.
     *
     * @param taskName The name of the task.
     * @param by The deadline of the task, in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);

    }

    /**
     * Returns a string representation of the deadline task in file storage format.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format(
            "%s | %s | %s | %s",
            TASK_SYMBOL, this.getIsDone() ? 1 : 0,
            this.getTaskname(),
            this.by.format(INPUT_FORMATTER)
            );
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string showing the task type, name, and deadline.
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + this.by.format(OUTPUT_FORMATTER) + ")";
        return s;
    }
}

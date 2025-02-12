package duchess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private static final String TASK_SYMBOL = "E";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param taskName The name of the event.
     * @param from The start time of the event, in the format "yyyy-MM-dd HHmm".
     * @param to The end time of the event, in the format "yyyy-MM-dd HHmm".
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the event task in file storage format.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format(
            "%s | %s | %s | %s | %s",
            TASK_SYMBOL, this.getIsDone() ? 1 : 0,
            this.getTaskname(),
            this.from.format(INPUT_FORMATTER),
            this.to.format(INPUT_FORMATTER)
            );
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string showing the task type, name, start time, and end time.
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString()
                + " (from: " + this.from.format(OUTPUT_FORMATTER)
                + ", to: " + this.to.format(OUTPUT_FORMATTER) + ")";
        return s;
    }
}

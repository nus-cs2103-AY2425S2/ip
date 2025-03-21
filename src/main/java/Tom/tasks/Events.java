package Tom.tasks;

import Tom.TomException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a start and end date
 */
public class Events extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an Event task with a start and end date.
     *
     * @param description The task description.
     * @param status If the task is completed or not
     * @param fromStr The start date in "yyyy-MM-dd" format.
     * @param toStr The end date in "yyyy-MM-dd" format.
     */
    public Events(String description, boolean status, String fromStr, String toStr) throws TomException {
        super(description, TaskType.EVENT, status);
        try {
            this.from = LocalDateTime.parse(fromStr + " 00:00", INPUT_FORMATTER);
            this.to = LocalDateTime.parse(toStr + " 00:00", INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TomException("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).");
        }
    }

    /**
     * Creates an Event task with a start and end date.
     *
     * @param description The task description.
     * @param status If the task is completed or not
     * @param from The start date in "yyyy-MM-dd" format.
     * @param to The end date in "yyyy-MM-dd" format.
     */
    public Events(String description, boolean status, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from.format(OUTPUT_FORMATTER) + " To: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}

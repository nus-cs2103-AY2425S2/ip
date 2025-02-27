package nyanko.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time in "yyyy-MM-dd HHmm" format.
     * @param to The end time in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Gets the start time of the event in input format.
     *
     * @return The start time formatted as "yyyy-MM-dd HHmm".
     */
    public String getFrom() {
        return this.from.format(INPUT_FORMAT);
    }

    /**
     * Updates the event start and end time.
     *
     * @param newDateTime The new start date/time in "yyyy-MM-dd HHmm" format.
     */
    public void snooze(String newDateTime) {
        this.from = LocalDateTime.parse(newDateTime, INPUT_FORMAT);
        this.to = from.plusHours(2); // Default duration 2 hours
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string showing the task type, status, start, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DISPLAY_FORMAT)
                + " to: " + this.to.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Converts the Event task into a save-friendly string format.
     *
     * @return A formatted string representing the task for saving.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("%s|%d|%s|%s|%s", this.getClass().getSimpleName(), isDone ? 1 : 0, description,
                this.from.format(formatter), this.to.format(formatter));
    }

    /**
     * Creates an Event task from a save file format.
     *
     * @param saveFormat The saved task string in the format: Event|doneStatus|description|startTime|endTime.
     * @return The reconstructed Event task.
     * @throws InvalidTaskFormatException If the format is invalid.
     */
    public static Task fromSaveFormat(String saveFormat) throws InvalidTaskFormatException {
        String[] parts = saveFormat.split("\\|");
        if (parts.length < 5) {
            throw new InvalidTaskFormatException("Invalid task format: " + saveFormat);
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String from = parts[3];
        String to = parts[4];

        Event event = new Event(description, from, to);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }
}

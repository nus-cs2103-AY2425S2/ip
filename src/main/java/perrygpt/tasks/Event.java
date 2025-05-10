package perrygpt.tasks;

import perrygpt.exceptions.PerryGPTException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a description, a start time, an end time, and a completion status.
 * Extends the {@link Task} class.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs an {@code Event} with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) throws PerryGPTException {
        super(description, TaskType.EVENT, isDone);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (Exception e) {
            throw new PerryGPTException("Invalid date format! " +
                    "Use: event <task_description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Converts this event into a file format string suitable for saving.
     *
     * @return A string representing this event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix() + super.description + " | " + this.from.format(INPUT_FORMAT)
                + " | " + this.to.format(INPUT_FORMAT);
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return from.format(INPUT_FORMAT);
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return to.format(INPUT_FORMAT);
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return super.description;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return A string representing this event, including its description, completion status,
     * start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from.format(OUTPUT_FORMAT) + " to "
                + to.format(OUTPUT_FORMAT) + ")";
    }
}
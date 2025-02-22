package julie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import julie.exception.WrongFormatException;


/**
 * Represents an event task that occurs within a specific time range.
 * An {@code Event} task includes a description, a start date/time, and an end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs an {@code Event} task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time in the format "dd-MM-yyyy HHmm".
     * @param by The end date and time in the format "dd-MM-yyyy HHmm".
     * @throws WrongFormatException If the date/time format is incorrect or the end time is before the start time.
     */
    public Event(String description, String from, String by) throws WrongFormatException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.endDateTime = LocalDateTime.parse(by, INPUT_FORMATTER);

            if (!this.endDateTime.isAfter(this.startDateTime)) {
                throw new WrongFormatException("The end date/time of an event must be after the start date/time!");
            }
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM");
        }

    }

    /**
     * Checks if this Event task is equal to another object.
     * Two Event tasks are considered equal if they have the same description, start date/time, and end date/time.
     *
     * @param obj The object to compare with this Event task.
     * @return {@code true} if the given object is an Event task with the same description, start date/time,
     *         and end date/time, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return this.description.equals(other.description)
                && this.startDateTime.equals(other.startDateTime)
                && this.endDateTime.equals(other.endDateTime);
    }

    /**
     * Returns the marker representing an Event task.
     * The marker "[E]" signifies that this task has a start and end time.
     *
     * @return The string marker "[E]".
     */
    @Override
    protected String getMarker() {
        return "[E]";
    }

    /**
     * Converts the {@code Event} task into a formatted string suitable for file storage.
     * The format follows: "E | {isDone} | {description} | {from} | {by}".
     *
     * @return A string representation of the {@code Event} task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + startDateTime.format(INPUT_FORMATTER) + " | " + endDateTime.format(INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the {@code Event} task,
     * including its status, description, start time, and end time.
     *
     * @return A formatted string representation of the {@code Event} task.
     */
    @Override
    public String toString() {
        return this.getMarker() + " " + super.toString() + " (from: "
                + startDateTime.format(OUTPUT_FORMATTER) + " to: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}

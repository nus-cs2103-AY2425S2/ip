package clovis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import clovis.ClovisException;

/**
 * The {@code Event} class represents a task with a specified start date and time, and a specified end date and time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    private LocalDateTime start;
    private LocalDateTime end;
    /**
     * Constructs a {@code Event} instance with the specified description, start date and time, and end date and time.
     *
     * @param description the description of the task.
     * @param startDateTime the start date and time of the task.
     * @param endDateTime the end date and time of the task.
     * @throws ClovisException if date and time format is invalid.
     */
    public Event(String description, String startDateTime, String endDateTime) throws ClovisException {
        super(description);
        try {
            this.start = LocalDateTime.parse(startDateTime, INPUT_FORMAT);
            this.end = LocalDateTime.parse(endDateTime, INPUT_FORMAT);
            if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
                throw new ClovisException("You can't travel to the past");
            }
        } catch (DateTimeParseException e) {
            throw new ClovisException("Invalid date and time format! Use: dd/MM/yyyy HHmm (e.g., 22/6/2000 1600)");
        }
    }

    /**
     * Constructs a {@code Event} instance with the specified description, completion status,
     * start date and time, and end date and time. For storage and retrieval purposes.
     *
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     * @param startDateTime the start date and time of the task.
     * @param endDateTime the end date and time of the task.
     */
    public Event(String description, boolean isDone, String startDateTime, String endDateTime) {
        super(description, isDone);
        this.start = LocalDateTime.parse(startDateTime, OUTPUT_FORMAT);
        this.end = LocalDateTime.parse(endDateTime, OUTPUT_FORMAT);
    }

    /**
     * Checks whether this event conflicts with another event ask.
     *
     * @param other the task to check for a scheduling conflict.
     * @return {@code true} if the other task is an {@code Event} with an
     *         overlapping time range, {@code false} otherwise.
     */
    @Override
    public boolean conflictsWith(Task other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.start.isBefore(otherEvent.end) && this.end.isAfter(otherEvent.start);
        }
        return false;
    }

    /**
     * Returns the string representation of the task formatted for file storage.
     *
     * @return the file format of the task with its task type, completion status,
     *     description, start date and time, and end date and time.
     */
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + start.format(OUTPUT_FORMAT) + " | " + end.format(OUTPUT_FORMAT);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string containing the task type, status icon,
     *     task description, start date and time, and end date and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(OUTPUT_FORMAT)
                + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}

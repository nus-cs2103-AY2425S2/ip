package julie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import julie.exception.WrongFormatException;


/**
 * Represents a task with a deadline.
 * A {@code Deadline} task includes a description and a due date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
    private final LocalDateTime dueDateTime;

    /**
     * Constructs a {@code Deadline} task with the given description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time in the format "dd-MM-yyyy HHmm".
     * @throws WrongFormatException If the date/time format is incorrect.
     */
    public Deadline(String description, String by) throws WrongFormatException {
        super(description);
        try {
            this.dueDateTime = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM");
        }
    }

    /**
     * Checks if this Deadline task is equal to another object.
     * Two Deadline tasks are considered equal if they have the same description and due date/time.
     *
     * @param obj The object to compare with this Deadline task.
     * @return {@code true} if the given object is a Deadline task with the same description and due date/time,
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return this.description.equals(other.description)
                && this.dueDateTime.equals(other.dueDateTime);
    }
    /**
     * Returns the marker representing a Deadline task.
     * The marker "[D]" signifies that this task has a due date.
     *
     * @return The string marker "[D]".
     */
    @Override
    protected String getMarker() {
        return "[D]";
    }

    /**
     * Converts the {@code Deadline} task into a formatted string suitable for file storage.
     * The format follows: "D | {isDone} | {description} | {by}".
     *
     * @return A string representation of the {@code Deadline} task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDateTime.format(INPUT_FORMATTER)
                + " | " + priority;
    }

    /**
     * Returns a string representation of the {@code Deadline} task,
     * including its status, description, and formatted due date/time.
     *
     * @return A formatted string representation of the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return this.getPriorityIcon() + " " + this.getMarker() + " " + super.toString()
                + " (by: " + dueDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}

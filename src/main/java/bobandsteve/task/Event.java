package bobandsteve.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bobandsteve.exception.InvalidCommandFormatException;
/**
 * Represents an event task in the BobAndSteve application.
 * An event task has a description, a status (completed or not), a start date/time, and an end date/time.
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Event task with the specified description, status, start, and end times.
     * The start and end times are represented by date and time strings in the format "YYYY-MM-DD HH:MM".
     *
     * @param description The description of the event task.
     * @param isDone The status of the task (completed or not).
     * @param start The start time of the event in "YYYY-MM-DD HH:MM" format.
     * @param end The end time of the event in "YYYY-MM-DD HH:MM" format.
     * @throws InvalidCommandFormatException If the date time format invalid or start time is after the end time.
     */
    public Event(String description, String isDone, String start, String end) throws InvalidCommandFormatException {
        super(description, isDone);
        try {
            this.startDateTime = LocalDateTime.parse(start, FORMATTER);
            this.endDateTime = LocalDateTime.parse(end, FORMATTER);

            if (startDateTime.isAfter(endDateTime)) {
                throw new InvalidCommandFormatException("Start date-time (" + startDateTime + ")"
                        + " must be before end date-time (" + endDateTime + ")");
            }
        } catch (DateTimeException error) {
            throw new InvalidCommandFormatException("Invalid date-time format. Expected: YYYY-MM-DD HH:MM");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(startDateTime)
                + " to: " + formatDateTime(endDateTime) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E" + " | " + super.toSaveFormat() + " | " + startDateTime.format(FORMATTER) + " | "
                + endDateTime.format(FORMATTER);
    }

    @Override
    public LocalDateTime getDeadline() {
        return startDateTime;
    }

}

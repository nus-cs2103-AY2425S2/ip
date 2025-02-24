package SirDuke.backend.task;
import SirDuke.backend.exception.IllegalStartAndEndDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task. A <code>EventTask<code> object has
 * a <code>description<code> String startTime that is the time that the event starts and
 * a <code>description<code> String endTime that is the time that the event ends
 */
public class EventTask extends Task {

    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * Constructor for an Event object
     *
     * @param description name of the task
     * @param startTime time that the task starts
     * @param endTime time that the task ends
     *
     * @throws DateTimeParseException if string is in an invalid format
     * @throws IllegalArgumentException if startTime is after endTime
     */
    public EventTask(String description, String startTime, String endTime)
            throws DateTimeParseException, IllegalStartAndEndDateException {
        super(description);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
        if (this.startTime.isAfter(this.endTime)) {
            throw new IllegalStartAndEndDateException(this.startTime, this.endTime);
        }
    }
    public void setStartTime(String newStartTime) throws DateTimeParseException {
        this.startTime = LocalDate.parse(newStartTime);
    }

    public void setEndTime(String newEndTime) throws DateTimeParseException {
        this.endTime = LocalDate.parse(newEndTime);
    }

    /**
     * Creates an entry to a file from an Event.
     * @return string representing the Event
     */
    @Override
    public String toFileEntry() {
        return "E|" + getStatusIcon() + "|" + description + "|"
                + startTime + "|" + endTime;
    }

    @Override
    public String getTaskType() {
        return "EVENT";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (start: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", end: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

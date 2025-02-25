package SirDuke.backend.task;

import SirDuke.backend.exception.IllegalStartAndEndTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task. A <c>EventTask</c> object has
 * a <c>LocalDateTime startTime</c> that is the time that the event starts and
 * a <c>LocalDateTime</c> endTime that is the time that the event ends
 */
public class EventTask extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;


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
            throws DateTimeParseException, IllegalStartAndEndTimeException {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, parsingFormatter);
        this.endTime = LocalDateTime.parse(endTime, parsingFormatter);
        if (this.startTime.isAfter(this.endTime)) {
            throw new IllegalStartAndEndTimeException(this.startTime, this.endTime);
        }
    }

    /**
     * @throws DateTimeParseException if string is in an invalid format
     * @throws IllegalStartAndEndTimeException if startTime is after endTime
     */
    public void setStartTime(String newStartTime) throws DateTimeParseException, IllegalStartAndEndTimeException {
        LocalDateTime proposedNewStartTime = LocalDateTime.parse(newStartTime, parsingFormatter);
        if (proposedNewStartTime.isAfter(this.endTime)) {
            throw new IllegalStartAndEndTimeException(proposedNewStartTime, this.endTime);
        }
        this.startTime = proposedNewStartTime;
    }

    /**
     * @throws DateTimeParseException if string is in an invalid format
     * @throws IllegalStartAndEndTimeException if startTime is after endTime
     */
    public void setEndTime(String newEndTime) throws DateTimeParseException, IllegalStartAndEndTimeException {
        LocalDateTime proposedNewEndTime = LocalDateTime.parse(newEndTime, parsingFormatter);
        if (this.startTime.isAfter(proposedNewEndTime)) {
            throw new IllegalStartAndEndTimeException(this.startTime, proposedNewEndTime);
        }
        this.endTime = proposedNewEndTime;
    }

    /**
     * Creates an entry to a file from an Event.
     * @return string representing the Event
     */
    @Override
    public String toFileEntry() {
        return "E|" + getStatusIcon() + "|" + description + "|"
                + startTime.format(parsingFormatter) + "|" + endTime.format(parsingFormatter);
    }

    @Override
    public String getTaskType() {
        return "EVENT";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (start: " + this.startTime.format(printingFormatter)
                    + ", end: " + this.endTime.format(printingFormatter) + ")";
    }
}

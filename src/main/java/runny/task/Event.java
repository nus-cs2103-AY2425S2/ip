package runny.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import runny.RunnyException;

/**
 * Represents an event task in Runny Chatbot.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event task with the given name, start time, and end time.
     *
     * @param description  The name of the event task.
     * @param from The start time of the event as a string.
     * @param to   The end time of the event as a string.
     */
    public Event(String description, String from, String to) throws RunnyException {
        super(description);
        this.from = convertTimeFrom(from);
        this.to = convertTimeTo(to);
        if (this.from.isAfter(this.to)) {
            throw new RunnyException("The time period is invalid");
        }
    }

    /**
     * Converts a start time string representation to a LocalDateTime object.
     *
     * @param dateTime The start time as a string.
     * @return The LocalDateTime object representing the start time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime convertTimeFrom(String dateTime) throws DateTimeException {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!dateTime.contains(" ")) {
            dateTime += " 0000";
        }
        return LocalDateTime.parse(dateTime, formatDate);
    }

    /**
     * Converts an end time string representation to a LocalDateTime object.
     *
     * @param dateTime The end time as a string.
     * @return The LocalDateTime object representing the end time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime convertTimeTo(String dateTime) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!dateTime.contains(" ")) {
            dateTime += " 2359";
        }
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Converts the event task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String save() {
        String saveFrom = this.from.toString().replace("T", " ").replace(":", "");
        String saveTo = this.to.toString().replace("T", " ").replace(":", "");
        return (super.isDone ? "1 " : "0 ") + "event " + super.description + "/from" + saveFrom + "/to" + saveTo;
    }

    /**
     * Converts the event task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

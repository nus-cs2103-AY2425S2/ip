package tom.task;

import java.time.LocalDate;

import tom.parser.Parser;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the task.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        assert description != null : "Description should not be null";
        assert start != null : "Start date should not be null";
        assert end != null : "End date should not be null";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task in file format.
     *
     * @return A string in the format "E | statusIcon | description".
     */
    @Override
    public String toFileFormatString() {
        return String.format(
            "E | %s | %s | %s | %s",
            getStatusIcon(),
            getDescription(),
            Parser.dateToString(start),
            Parser.dateToString(end)
        );
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[E][statusIcon] description (from: startDate to: endDate)".
     */
    @Override
    public String toString() {
        String startString = Parser.dateToString(start);
        String endString = Parser.dateToString(end);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startString, endString);
    }
}

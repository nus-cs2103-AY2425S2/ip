package task;

import java.time.LocalDateTime;
import parser.DateParser;

import exception.SunpterException;


/**
 * Event class. A task that contains a start and end date
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String name, String start, String end, int priority) throws SunpterException {
        super(name, priority);
        this.start = DateParser.parseDate(start);
        this.end = DateParser.parseDate(end);

        if(this.end.isBefore(this.start)) {
            throw new SunpterException("Start date cannot be after end date");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + DateParser.formatDate(start) + " to: " + DateParser.formatDate(end) + ")";
    }

    @Override
    public String toStorageString() {
        return "[E]" + " " + this.getStatus() + " " + getPriority() + " " + getDescription() + " /from " +
                DateParser.parseStorage(start) + " /to " + DateParser.parseStorage(end);
    }
}
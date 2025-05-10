package dynamis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task in with a start and end duration in datetime format.
 */
public class Event extends Task {
    protected String startString;
    protected String endString;
    protected LocalDate start;
    protected LocalDate end;

    private static final String DATETIME_FORMAT = "MMM d yyyy";

    /**
     * Constructs a Event Object. A type of task for the tasklist.
     *
     * @param name The name or description of the task.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public Event(String name, String start, String end) {
        super(name);
        try {
            this.start = LocalDate.parse(start);
        } catch (DateTimeParseException e) {
            this.startString = start;
            this.start = null;
        }
        try {
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            this.endString = end;
            this.end = null;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + (start != null
                    ? start.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
                    : startString) + " to: "
                + (end != null
                    ? end.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
                    : endString) + ")";
    }
}

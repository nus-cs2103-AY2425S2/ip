package orim.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a subclass of Task which happens in a period of time.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String isDone, String description, String from, String to) {
        super(description, isDone.equals("1"));
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Transforms the state and detail of the tasks to a string.
     * Will be store in the data file and be reloaded when user open Orim again.
     *
     * @return log to represents the state and detail of the tasks
     */
    public String generateLog() {
        return "E," + (isDone ? 1 : 0) + "," + super.description + "," + from + "," + to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
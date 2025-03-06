package paimon.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of Todo that has a start and end time.
 */
public class Event extends Todo {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     * 
     * @param description same as description in Todo.
     * @param from the start time of the event in format "d/M/yyyy HHmm".
     * @param to the end time of the event in format "d/M/yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override 
    public String getType() {
        return "[E]";
    }

    @Override
    public String to_save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String status = this.isDone ? "1" : "0";
        String fromFormatted = this.from.format(formatter);
        String toFormatted = this.to.format(formatter);

        return String.format("%s | %s | %s | %s | %s", 
                     getType(), status, getDescription(), fromFormatted, toFormatted);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() 
            + " (at: " + this.from.format(formatter) 
            + " to " + this.to.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event e = (Event) obj;
            return super.equals(e) && this.from.equals(e.from) && this.to.equals(e.to);
        } else {
            return false;
        }
    }
}

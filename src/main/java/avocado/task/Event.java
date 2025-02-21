package avocado.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at specific times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     * @param description Description of the event.
     * @param from Starting time of the event.
     * @param to Ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")); 
    }

    /**
     * Gets the starting time of the event.
     * @return The starting time of the event.
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the ending time of the event.
     * @return The ending time of the event.
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }  

    /**
     * Returns the string representation of the event.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
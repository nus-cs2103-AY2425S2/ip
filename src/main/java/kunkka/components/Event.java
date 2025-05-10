package kunkka.components;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event.
     *
     * @param name Name of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     * @param isDone Status of the event.
     * @param priority Priority of the event.
     */
    public Event(String name, String from, String to, boolean isDone, int priority) {
        super(name, "E", isDone, priority);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the start date of the event.
     * @return Start date of the event.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     * @return End date of the event.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the duration of the event in days.
     *
     * @return Duration of the event in days.
     */
    public long getDuration() {
        return ChronoUnit.DAYS.between(from, to);
    }

    /**
     * Returns the formatted start date of the event.
     *
     * @return Formatted start date of the event.
     */
    public String getFromFormatted() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the formatted end date of the event.
     *
     * @return Formatted end date of the event.
     */
    public String getToFormatted() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + name + " (from: " + getFromFormatted() + " to: " + getToFormatted() + ")" + " (Priority: " + priority + ")";
    }
}
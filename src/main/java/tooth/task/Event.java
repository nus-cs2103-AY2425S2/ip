package tooth.task;

import java.time.LocalDate;

/**
 * Event Task Object
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    private Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public static Event of(String name, LocalDate from, LocalDate to) {
        return new Event(name, false, from, to);
    }

    public static Event of(String name, boolean isDone, LocalDate from, LocalDate to) {
        return new Event(name, isDone, from, to);
    }

    @Override
    public Event complete() {
        return new Event(this.name, true, this.from, this.to);
    }

    @Override
    public Event incomplete() {
        return new Event(this.name, false, this.from, this.to);
    }

    @Override
    public String serialize() {
        return "E|" + name + "|" + to + "|" + from + "|" + isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from + ", To: " + to + ")";
    }
}

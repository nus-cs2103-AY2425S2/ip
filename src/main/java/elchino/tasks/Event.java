package elchino.tasks;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for an event task.
     * @param description The description of the event task.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    /**
     * Retrieves the start date and time of the event.
     * @return The formatted start date and time of the event.
     */
    public String getFrom() {
        return start.format(OUTPUT_DATE_FORMAT);
    }

    /**
     * Retrieves the end date and time of the event.
     * @return The formatted end date and time of the event.
     */
    public String getBy() {
        return end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFrom(), getBy());
    }

    @Override
    public String storeTask() {
        return String.format("E | %d | %s | %s | %s",
                             isDone ? 1 : 0, description,
                             start.format(INPUT_DATE_FORMAT),
                             end.format(INPUT_DATE_FORMAT));
    }
}
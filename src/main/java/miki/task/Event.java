package miki.task;

import java.time.LocalDateTime;

/**
 * Event class represents a task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Event class.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, String... tags) {
        super(description, TaskType.EVENT, tags);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task in storage format.
     *
     * @return task in storage format.
     */
    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat()
                + " | " + start + " | " + end;
    }

    /**
     * Returns the task in string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "\n"
                + "        From  "
                + start.getDayOfMonth() + " "
                + start.getMonth() + " "
                + start.getYear() + ", "
                + String.format("%02d", start.getHour()) + ":"
                + String.format("%02d", start.getMinute())
                + "  to  "
                + end.getDayOfMonth() + " "
                + end.getMonth() + " "
                + end.getYear() + ", "
                + String.format("%02d", end.getHour()) + ":"
                + String.format("%02d", end.getMinute());
    }
}

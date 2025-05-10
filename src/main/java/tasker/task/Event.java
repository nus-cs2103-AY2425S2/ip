package tasker.task;

import java.time.LocalDateTime;

/**
 * A task with a starting and ending time.
 */
public class Event extends DateTimeTask {
    /** The starting time of this task */
    private LocalDateTime start;
    /** The ending time of this task */
    private LocalDateTime end;

    /**
     * Class constructor.
     *
     * @param description The description of this task.
     * @param start       The starting time of this task.
     * @param end         The ending time of this task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.E);
        this.start = start;
        this.end = end;
    }

    /**
     * Class constructor.
     *
     * @param description The description of this task.
     * @param isDone      Whether this task is done.
     * @param start       The starting time of this task.
     * @param end         The ending time of this task.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.E, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toStorage() {
        return String.format("%s|%s|%s", super.toStorage(), this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.formatOutput(this.start),
                this.formatOutput(this.end));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Event event = (Event) obj;
        return (start == null ? event.start == null : start.equals(event.start))
                && (end == null ? event.end == null : end.equals(event.end));
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (start == null ? 0 : start.hashCode());
        result = 31 * result + (end == null ? 0 : end.hashCode());
        return result;
    }
}

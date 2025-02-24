package boink.tasks;

import java.time.LocalDateTime;

import boink.utils.Utils;

/**
 * This class represents a task with a start and end datetime.
 */

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Event class.
     * @param name The name of task.
     * @param start The start datetime of task.
     * @param end The end datetime of task.
     */

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates output in format for saving task.
     * @return Task as string to write to file.
     */

    @Override
    public String saveTask() {
        return String.format(
                "E | %d | %s | %s | %s",
                super.getDoneIntValue(),
                this.getName(),
                Utils.saveDateTime(this.start),
                Utils.saveDateTime(this.end)
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[E] %s from %s to %s",
                super.toString(),
                Utils.getDateTime(this.start),
                Utils.getDateTime(this.end)
        );
    }
}

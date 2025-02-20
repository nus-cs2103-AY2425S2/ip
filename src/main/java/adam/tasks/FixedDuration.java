package adam.tasks;

import java.time.Duration;
import java.time.LocalDate;

import adam.exceptions.AdamException;
import adam.parser.Parser;

/**
 * Represents a task that has a fixed duration.
 */
public class FixedDuration extends Task {
    /** Duration of the task */
    private Duration duration;
    /**
     * Constructor for adam.tasks.FixedDuration.
     *
     * @param description Description of the task.
     * @param duration Duration of the task.
     * @throws AdamException If the description is empty.
     */
    public FixedDuration(String description, Duration duration) throws AdamException {
        super(description);
        this.duration = duration;
    }

    /**
     * Gets the task as a String.
     */
    @Override
    public String toString() {
        return String.format("[F] %s (Duration: %s)", super.toString(), Parser.formatDuration(duration));
    }

    /**
     * Gets the task as a String for logging.
     */
    @Override
    public String toLogString() {
        return "F | " + super.toLogString() + " | " + Parser.formatDuration(duration);
    }

    /**
     * Checks if it is on the specified date.
     *
     * @param date The date to check against.
     * @return False, as a FixedDuration task has no due date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        return false;
    }
}

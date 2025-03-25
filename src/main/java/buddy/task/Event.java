package buddy.task;

import java.time.LocalDateTime;

import buddy.command.Command;
import buddy.exception.BuddyException;

/**
 * Represents Event type
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the Event task.
     * @param from        Start time of the Event task.
     * @param to          End time of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageStringFormat() {
        String result = "E | ";
        result += this.isDone ? "1" : "0";
        result += " | " + this.description + " | ";
        result = result + this.from.format(Task.PATTERN_STORE) + " | " + this.to.format(Task.PATTERN_STORE) + "\n";
        return result;
    }

    @Override
    public void updateTask(String field, String newValue) throws BuddyException {
        switch (field) {
        case "/from" -> this.from = Command.getDateAndTime(newValue);
        case "/to" -> this.to = Command.getDateAndTime(newValue);
        case "/description" -> this.description = newValue;
        default -> throw new BuddyException("Invalid update field for Event! Use '/from', '/to', '/description'.");
        }
    }

    /**
     * Returns string representation of Event task.
     *
     * @return string representation of Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.format(Task.PATTERN_WRITE), to.format(Task.PATTERN_WRITE));
    }
}

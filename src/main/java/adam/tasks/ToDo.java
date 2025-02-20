package adam.tasks;

import java.time.LocalDate;

import adam.exceptions.AdamException;

/**
 * Represents a task that has to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) throws AdamException {
        super(description);
    }

    /**
     * Gets the task as a String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the task as a String for logging.
     */
    @Override
    public String toLogString() {
        return "T | " + super.toLogString();
    }

    /**
     * Checks if it is on the specified date.
     *
     * @param date The date to check against.
     * @return False, as a ToDo task has no due date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        return false;
    }
}

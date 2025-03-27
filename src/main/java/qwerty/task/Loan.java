package qwerty.task;

import qwerty.exceptions.EmptyTaskNameException;

/**
 * Represents a Loan task.
 * Inherits from {@link Task} and provides methods for formatting and saving the task.
 */
public class Loan extends Task {

    private final String FROM;
    private final String TO;

    /**
     * Constructs a Loan task with the specified name, start time, and end time.
     *
     * @param name The name or description of the Loan task.
     * @param from The person loaned from.
     * @param to The person loaned to.
     * @throws EmptyTaskNameException If the task name is empty.
     */
    public Loan(String name, String from, String to) throws EmptyTaskNameException {
        super(name);
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Returns a string representation of the Loan task.
     *
     * @return A string representing the Loan task.
     */
    @Override
    public String toString() {
        return "[L]" + super.toString() + " (from: " + FROM + " to: " + TO + ")";
    }

    /**
     * Returns a string representation of the Loan task in a format suitable for saving to a file.
     *
     * @return A string representing the Loan task in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "L | " + (this.isDone ? "1 | " : "0 | ") + this.name + " | " + FROM + " | " + TO;
    }
}

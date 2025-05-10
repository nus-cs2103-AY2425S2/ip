package partillay.task;

import java.time.LocalDateTime;

import partillay.parser.DateTimeFormatParser;

/**
 * Represents a Deadline task in the task list.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected String byString;

    /**
     * Instantiates a new Deadline task with the given description and deadline time.
     *
     * @param description the description of the task
     * @param by the deadline time for the task in string format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeFormatParser.parseDateTime(by);
        this.byString = by;
    }

    /**
     * Instantiates a new Deadline task with the given description, deadline time, and task status.
     *
     * @param description the description of the task
     * @param by the deadline time for the task in string format
     * @param statusBinaryNumber the status of the task in binary representation (e.g., done or not done)
     */
    public Deadline(String description, String by, String statusBinaryNumber) {
        super(description, statusBinaryNumber);
        this.by = DateTimeFormatParser.parseDateTime(by);
        this.byString = by;
    }

    /**
     * Returns a string representation of the Deadline task in the format:
     * [D] description (by: deadline)
     *
     * @return a formatted string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + DateTimeFormatParser.getFormattedDateString(by)
                + ")";
    }

    /**
     * Returns the string format of the Deadline task that can be saved to a file.
     * The format is: D | status | description | by
     *
     * @return a formatted string representing the task for file storage
     */
    public String getTxtFormat() {
        return "D"
                + " | "
                + getStatusBinaryNumber()
                + " | "
                + description
                + " | "
                + by;
    }

    /**
     * Compares this Deadline task to another object for equality.
     * Two Deadline tasks are considered equal if their descriptions and deadline times are the same.
     *
     * @param other the object to compare to
     * @return true if both Deadline tasks have the same description and deadline time, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deadline) {
            return description.equals(((Deadline) other).description)
                    && by.equals(((Deadline) other).by);
        }
        return false;
    }
}

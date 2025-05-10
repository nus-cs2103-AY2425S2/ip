package phil.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task which has a description and a by-date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("M/d/yy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy h.mma");
    private String byDate;
    private LocalDateTime byDateInDateTime;

    /**
     * Sets up the Deadline object
     *
     * @param description String which describes the deadline task.
     * @param byDate String which represents the deadline of the task.
     */
    public Deadline(String description, String byDate) {
        super(description);
        // Check if byDate is in datetime format, and parse it as datetime if so.
        try {
            LocalDateTime byDateInDateTime = LocalDateTime.parse(byDate, Deadline.INPUT_FORMATTER);
            this.byDate = null;
            this.byDateInDateTime = byDateInDateTime;
        } catch (DateTimeParseException e) {
            this.byDate = byDate;
            this.byDateInDateTime = null;
        }

        // Assert that either byDate or byDateInDateTime is not null
        assert this.byDateInDateTime != null || this.byDate != null;
    }

    /**
     * Returns the string representation of the deadline (by-date).
     * If a LocalDateTime was provided as the bydate, it formats it based on the formatter provided.
     * Else, the String bydate is returned.
     *
     * @param formatter formatter to format the LocalDateTime object representing the by date to.
     * @return String representation of the by date.
     */
    public String byDateToString(DateTimeFormatter formatter) {
        if (this.byDate != null) {
            return this.byDate;
        } else {
            return this.byDateInDateTime.format(formatter);
        }
    }

    /**
     * Returns the string representation of Deadline object for printing when list is called.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.byDateToString(Deadline.OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns String representation of Deadline object for use by Storage class.
     *
     * @return String representation of the Deadline object to be stored and loaded by Storage class.
     */
    @Override
    public String toLoadString() {
        return "Deadline - " + super.toLoadString() + " - "
                + this.byDateToString(Deadline.INPUT_FORMATTER);
    }

}
